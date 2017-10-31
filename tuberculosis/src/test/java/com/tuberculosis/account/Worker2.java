package com.tuberculosis.account;

/**
 * Created by Li Shaoqing
 * on 16/8/6.
 */
public class Worker2 extends Thread {

    public Worker2(Test t) {
        this.t = t;
    }

    private Test t;

    public void run() {
        while (true) {
//            t.l.set(1l);
            t.l = 1;
        }
    }

}
