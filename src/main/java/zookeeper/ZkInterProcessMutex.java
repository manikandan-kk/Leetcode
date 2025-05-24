package zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkInterProcessMutex {

    private CuratorFramework client;
    private static final String LOCK_ROOT = "/mylock";

    public ZkInterProcessMutex() {
        this.client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(10000)
                .sessionTimeoutMs(10000)
                .build();
        this.client.start();
        try {
            Stat stat = client.checkExists().forPath(LOCK_ROOT);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(LOCK_ROOT, "first".getBytes());
            }
        } catch(Exception e) {
        }
    }

    private void executableJob() {
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void thread1() {
        String pathToLock = LOCK_ROOT + "/ap_simulator/planId=124";
        try {
            if (client.checkExists().forPath(pathToLock) == null) {
                String x = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(pathToLock);
                log.info("T1..." + x);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        InterProcessMutex interProcessMutex = new InterProcessMutex(this.client, pathToLock);
        try {
            interProcessMutex.acquire(60, TimeUnit.MINUTES);
            log.info("Acquired from T1");
            executableJob();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
               // validateSomething();
                //interProcessMutex.release();
                release(interProcessMutex);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void thread2() {
        String pathToLock = LOCK_ROOT + "/ap_simulator/planId=124";
        try {
            if (client.checkExists().forPath(pathToLock) == null) {
                String x = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(pathToLock);
                log.info("T2..." + x);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        InterProcessMutex interProcessMutex = new InterProcessMutex(this.client, pathToLock);
        try {
            interProcessMutex.acquire(60, TimeUnit.MINUTES);
            log.info("Acquired from T2: ");
            executableJob();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //validateSomething();
                release(interProcessMutex);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void release(InterProcessMutex interProcessMutex) throws Exception {
        if (interProcessMutex.isAcquiredInThisProcess()) {
            log.info("Hey man!" + " " + Thread.currentThread().getName());
            interProcessMutex.release();
        }
    }

//    private void validateSomething() {
//        String pathToLock = LOCK_ROOT + "/ap_simulator/planId=124";
//        InterProcessMutex interProcessMutex = new InterProcessMutex(this.client, pathToLock);
//        if (interProcessMutex.isAcquiredInThisProcess()) {
//            log.info("Hey man!" + " " + Thread.currentThread().getName());
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        ZkInterProcessMutex zkInterProcessMutex = new ZkInterProcessMutex();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            zkInterProcessMutex.thread1();
            // zkInterProcessMutex.validateSomething();
        });
        executorService.submit(() -> {
            zkInterProcessMutex.thread2();
            //zkInterProcessMutex.validateSomething();
        });

        Thread.sleep(3000);
        //zkInterProcessMutex.validateSomething();

        Thread.sleep(1000000);
    }

}
