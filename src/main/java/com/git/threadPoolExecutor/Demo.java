package com.git.threadPoolExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(10,10,2000, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {

        for (int i = 0; i <50 ; i++) {
            THREAD_POOL.submit(new Demo.innerTask(i+"",i+""));

            System.out.println("当前循环次数"+i);
            System.out.println("当前队活跃数"+THREAD_POOL.getActiveCount());
            System.out.println("当前队列数"+THREAD_POOL.getQueue().size());
        }
        boolean flag = true;
        while (flag){
            if (THREAD_POOL.getActiveCount()==0){
                System.out.println("线程全部执行完毕了,程序结束");
                flag = false;
            }
        }
    }



    public static class innerTask implements Callable<String>{
        private String pamam1;
        private String pamam2;
        public innerTask(String pamam1,String pamam2){
            this.pamam1=pamam1;
            this.pamam2=pamam2;
        }

        public String call() throws Exception {
            Thread.sleep(1000);
            System.out.println("线程："+pamam1);
            System.out.println("线程："+pamam2);
            return null;
        }
    }
}
