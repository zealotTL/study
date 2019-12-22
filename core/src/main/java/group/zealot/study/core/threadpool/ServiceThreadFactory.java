package group.zealot.study.core.threadpool;

import java.util.concurrent.ThreadFactory;

public class ServiceThreadFactory implements ThreadFactory {

    @Override
    public ServiceThread newThread(Runnable r) {
        return new ServiceThread(r);
    }
}
