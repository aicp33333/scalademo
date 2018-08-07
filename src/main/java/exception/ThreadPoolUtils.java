package exception;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rongpei
 * @Description: ${todo}
 * @date 2018/7/28
 */
public class ThreadPoolUtils {
    private static ThreadPoolExecutor mongoThreadPoolExecutor = null;
    private static ThreadPoolExecutor mysqlThreadPoolExecutor = null;
    static {
        int mysqlThreadPoolMinSize=3;
        int mongoThreadPoolMinSize=7;
        int mysqlThreadPoolMaxSize=30;
        int mongoThreadPoolMaxSize=40;
        int maxQueueDepth = (mysqlThreadPoolMinSize+mysqlThreadPoolMaxSize)*2;
        mongoThreadPoolExecutor = new ThreadPoolExecutor(mongoThreadPoolMinSize, mongoThreadPoolMaxSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(maxQueueDepth),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        mongoThreadPoolExecutor.allowCoreThreadTimeOut(true);

        mysqlThreadPoolExecutor = new ThreadPoolExecutor(mysqlThreadPoolMinSize, mysqlThreadPoolMaxSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(maxQueueDepth*5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        mysqlThreadPoolExecutor.allowCoreThreadTimeOut(true);
    }
    public static ThreadPoolExecutor getMongoThreadPoolExecutor(){
        return mongoThreadPoolExecutor;
    }
    public static ThreadPoolExecutor getMysqlThreadPoolExecutor(){
        return mysqlThreadPoolExecutor;
    }

}
