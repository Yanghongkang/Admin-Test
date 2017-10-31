package com.tuberculosis.account;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Created by Li Shaoqing
 * on 16/8/6.
 */
public class Test extends DataSource{

//    protected AtomicLong l = new AtomicLong(-1);

	
    protected  int l = -1;
    public static void main(String[] args) {
        System.out.println(toBinary(-1l));
        System.out.println(toBinary(1l));
        System.out.println("dddd");
        Test t = new Test();
        Worker w1 = new Worker(t);
        Worker2 w2 = new Worker2(t);
        w1.setDaemon(true);
        w2.setDaemon(true);
        w1.start();
        w2.start();
        while (true) {
            System.out.println(t.l);
            if (t.l != -1 && t.l != 1) {

                long a = t.l;
                System.out.println("----" + a + "------");
                System.out.println(toBinary(t.l));
                System.out.println(t.l);
                System.out.println(t.l != -1);
                System.out.println(t.l != 1);
                System.out.println(t.l != -1 && t.l != 1);

                System.out.println("l的写不是原子操作");
//                break;
            }
        }
    }

    private static String toBinary(long l) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(l));
        while (sb.length() < 64) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}
