package com.hsgene.test;

import org.junit.Test;

/**
 * Created by hjc on 2016/12/12.
 */
public class ThreadLocalTest {

    private static final ThreadLocal threadId = new ThreadLocal();
    private static final ThreadLocal threadName = new ThreadLocal();

    /**
     * 使用场景：数据源、session；
     * 意义：每个线程只能访问各自的内部变量，其它线程无法访问，这样是线程安全的，
     * 一个web请求会从线程池中new一个线程来处理，处理完会放回线程池等待下一个请求，如果同时有其他请求，则取线程池中的其它线程，从中创建需要的变量，
     * 这样多线程之间既同步又安全，如果是单例则需要强制同步，肯定会影响性能
     * 源码：创建一个ThreadLocal对象（设为静态或者随类一起加载，该对象只需初始化一次），通过该对象的get()方法获取需要的变量(注意强转)，
     * get()方法实现：获取当前线程对象的成员ThreadLocalMap对象，ThreadLocalMap对象是ThreadLocal的内部类，通过ThreadLocalMap的getEntry()方法获取
     * 将ThreadLocal对象作为key，你需要保存的变量作为value，set到ThreadLocalMap中(该key和value是以ThreadLocalMap的内部类对象Entry存在，该对象继承WeakReference，在不使用时会被自动回收)，
     * 总结：使用ThreadLocal保证了当前用户的线程只能使用自己的变量，因为同一时刻一个线程只会被一个请求使用，不会跨线程使用变量，
     * 这样同步也只局限于线程内部，同时多线程运行时就能既同步性能影响也不大
     * 核心：同步会影响性能，使用ThreadLocal只需要线程同步，而单例则需要进程或整个应用都同步；
     * 绑定线程和变量，这样就不需要反复创建对象(例数据源)，需要时从线程中取，当不再被使用时，因为继承自弱引用，其还会被自动回收；
     * 一个线程创建多个ThreadLocal对象，即可以绑定多个变量
     */
    @Test
    public void testThreadLocal() throws InterruptedException {
        threadId.set(Thread.currentThread().getId());
        threadName.set(Thread.currentThread().getName());
        System.out.println(threadId.get());
        System.out.println(threadName.get());
        Thread thread = new Thread() {
            @Override
            public void run() {
                threadId.set(Thread.currentThread().getId());
                threadName.set(Thread.currentThread().getName());
                System.out.println(threadId.get());
                System.out.println(threadName.get());
            }
        };
        thread.start();
        thread.join();
        System.out.println(threadId.get());
        System.out.println(threadName.get());
    }

}
