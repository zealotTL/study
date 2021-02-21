package group.zealot.study.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public interface ThreadPoolManager {

    void execute(Runnable r) throws RejectedExecutionException;

    <T> Future<T> submit(Callable<T> task);
}
