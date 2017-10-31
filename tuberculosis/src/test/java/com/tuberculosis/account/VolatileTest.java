package com.tuberculosis.account;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Li Shaoqing
 * on 16/9/5.
 */
public class VolatileTest {

    private volatile int vo = 0;

//    private AtomicInteger ai = new AtomicInteger();

    public void incVo() {
        vo ++;

    }

//    public void incAi() {
//        ai.incrementAndGet();
//    }

    public static void main(String[] args) {
//        VolatileTest test = new VolatileTest();
//
//        test.incVo();
////        test.incAi();
    }
}
