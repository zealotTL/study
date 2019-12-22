package group.zealot.study.core.threadpool;

public class ServiceThread extends Thread {
    public ServiceThread(Runnable r) {
        super(r);
    }
}