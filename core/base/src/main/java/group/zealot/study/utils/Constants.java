package group.zealot.study.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {
    /**
     * 目录分割符
     */
    public static final String FILE_SEPARATOR_CHAR = File.separatorChar + "";

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 换行符
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

}
