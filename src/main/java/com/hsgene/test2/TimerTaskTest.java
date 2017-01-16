package com.hsgene.test2;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjc on 2017/1/16.
 */
public class TimerTaskTest {

    //  创建多线程一般都用Runnable方式，代码可以复用
    //  注意：JunitTest单元中不支持多线程，因为其已经封装了main方法，方法结束便会调用System.exit()方法
    //  所以测试多线程时可以在main方法中进行，当然也有解决方案：countDownLatch.await()使主线程阻塞直到子线程结束或阻塞超时

    /**
     * 定时任务方式一
     */
    @Test
    public void testSleep() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int i = 1;
                while (true) {
                    if (i > 5) {
                        break;
                    }
                    System.out.println("从1数到5======" + i);
                    i++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        try {
            new CountDownLatch(1).await(2, TimeUnit.MINUTES);// 使主线程阻塞2分钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务方式二
     */
    @Test
    public void testTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务timer");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 2000); //  控制第一次启动任务延时时长，并且timer.cancel()方法可以主动结束任务
        try {
            new CountDownLatch(1).await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务方式三
     * 该方式首选，具有Timer的特性，同时基于线程池来执行任务，而Timer则是单线程
     * @param args
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("定时任务ScheduledExecutorService");
            }
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);
    }
}
