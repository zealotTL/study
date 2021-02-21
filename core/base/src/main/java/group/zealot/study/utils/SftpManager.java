package group.zealot.study.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

public class SftpManager {
    private static Logger logger = LoggerFactory.getLogger(SftpManager.class);

    private static ConcurrentHashMap<String, ChannelSftp> sftps = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Integer> sftpsTimes = new ConcurrentHashMap<>();//次数
    private static ConcurrentHashMap<String, LocalDateTime> sftpsTime = new ConcurrentHashMap<>();//时间

    private static final long sleep_time = 1500L;//获取连接异常等待时间
    private static final int tryTimes = 3;//重连失败次数

    /**
     * 生成一个sftp连接
     *
     * @param sftpName sftp的名称
     */
    public static synchronized ChannelSftp getSftp(String sftpName) {
        ChannelSftp sftp = sftps.get(sftpName);

        if (!sftpIsAlive(sftp, sftpName)) {
            boolean first = true;
            int time = 0;
            do {
                if (!first) {
                    try {
                        logger.info("停1.5秒，再建立连接");
                        Thread.sleep(SftpManager.sleep_time);
                    } catch (InterruptedException e) {
                        logger.info("Thread.sleep(1500)异常", e);
                    }
                }
                ChannelSftp old = sftp;
                SftpUtil.disConnectedSftp(old);
                logger.info("重新建立新连接" + sftpName);
                try {
                    sftp = SftpUtil.connect(sftpName);
                    sftpsTime.put(sftpName, LocalDateTime.now());
                    sftpsTimes.put(sftpName, 0);
                } catch (JSchException e) {
                    time++;
                    logger.error("重新建立新连接【异常】" + sftpName, e);
                    if (time >= SftpManager.tryTimes) {
                        throw new RuntimeException("连接sftp异常，超出连接次数");
                    }
                }
                first = false;
            } while (!sftpIsAlive(sftp, sftpName));
            sftps.put(sftpName, sftp);
        } else {
            Integer times = sftpsTimes.get(sftpName);
            if (times == null) {
                times = 0;
            }
            sftpsTimes.put(sftpName, times + 1);
        }
        return sftp;
    }

    /**
     * 判断此sftp 是否可用
     */
    private static boolean sftpIsAlive(ChannelSftp sftp, String keyName) {
        try {
            if (sftp == null || !sftp.isConnected() || !sftp.getSession().isConnected()) {
                logger.warn("sftp is unconnected");
                return false;
            }
            Integer times = sftpsTimes.get(keyName);
            if (times != null && times > 100) {
                logger.warn("此连接 获取次数超过100，重新连接 times " + times);
                return false;
            }
            LocalDateTime now = sftpsTime.get(keyName);
            if (now != null && now.plusMinutes(1L).isBefore(LocalDateTime.now())) {
                logger.warn("此连接 获取时间超过1min，重新连接 time " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                return false;
            }
            sftp.cd("/");
            logger.debug("sftp is connected ,cd / success");
            return true;
        } catch (Exception e) {
            logger.error("sftp cd / 异常", e);
            return false;
        }
    }
}
