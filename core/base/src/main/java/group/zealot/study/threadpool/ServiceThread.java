package group.zealot.study.threadpool;

public class ServiceThread extends Thread {
    public ServiceThread(Runnable r) {
        super(r);
    }
}