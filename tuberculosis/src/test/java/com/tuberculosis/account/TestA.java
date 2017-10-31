package com.tuberculosis.account;

/**
 * Created by Li Shaoqing
 * on 16/8/6.
 */
public class TestA {

    private long b = 0;

    public void set1(){
        b = 0;
    }

    public void set2(){
        b = -1;
    }

    public void check() {
        System.out.println(b);
        if(0 != b && -1 !=b){
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        final TestA t = new TestA();

        final Thread t1 = new Thread() {
            public void run(){
                while(true) {
                    t.set1();
                }

            }
        };
        t1.start();

        final Thread t2 = new Thread() {
            public void run(){
                while (true) {
                    t.set2();
                }

            }
        };
        t2.start();

        final Thread t3 = new Thread() {
            public void run(){
                while(true) {
                    t.check();
                }

            }
        };
        t3.start();
    }
}
