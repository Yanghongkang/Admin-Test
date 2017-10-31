package com.tuberculosis.test;

import java.io.*;

/**
 * Created by Li Shaoqing
 * on 16/9/11.
 */
public class IO {

    public static void main(String[] args) {
        new IO().test();
    }

    private void test() {
        ReadThread rt = new ReadThread();
        Thread t1 = new Thread(rt, "read");

        WriteThread wt = new WriteThread();
        Thread t2 = new Thread(wt, "write");

        t1.start();
        t2.start();
    }

    protected class WriteThread implements Runnable {

        public void run() {
            try{
                File f = new File("/Users/wind-lsq/tmp/io.txt");
                FileOutputStream fos = new FileOutputStream(f);
                int count = 1;
                while(true) {
                    byte[] b = ("hello" + count + "\n").getBytes();
                    fos.write(b);
                    fos.flush();
                    count ++;
                    Thread.sleep(1000);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected class ReadThread implements Runnable {
        public void run() {
            try{
                File f = new File("/Users/wind-lsq/tmp/io.txt");
                FileInputStream fi = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fi);
                BufferedReader br = new BufferedReader(isr);
                while (true) {
                    String line = br.readLine();
                   if(line != null) {
                        System.out.println(line);
                    }

                }
//                int readData;
//                while(true) {
//                    readData = fi.read();
//                    if(readData == -1) {
//                        System.out.println("no  data");
//                        Thread.sleep(1000);
//                    } else {
//                        System.out.println(readData);
//                    }
//
//
//                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
