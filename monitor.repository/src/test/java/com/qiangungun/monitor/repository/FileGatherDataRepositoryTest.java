/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FileGatherDataRepositoryTest.java, v0.1 2016年11月22日 下午7:37:56 czhou3306@gmail.com Exp $
 */
public class FileGatherDataRepositoryTest extends TestCase {

    public void testSplit() {
        String s = "$2";
        String[] result = s.split("\\$");
        for (String str : result) {
            System.err.println(str);
            
           // System.err.println(Integer.parseInt(str));
        }
        
    }

    public void testP() {

        long start = System.currentTimeMillis();
        File file = new File("D:/11.txt");
        RandomAccessFile fout = null;
        FileChannel fcout = null;
        try {
            fout = new RandomAccessFile(file, "rw");
            String line = null;

            int i = 0;
            while ((line = fout.readLine()) != null) {
                //System.err.println(line);
                i++;
            }
            System.err.println(System.currentTimeMillis() - start);

            /*long filelength = fout.length();//获取文件的长度
            fout.seek(filelength);//将文件的读写指针定位到文件的末尾
            fcout = fout.getChannel();//打开文件通道
            FileLock flout = null;
            while (true) {
                try {
                    flout = fcout.tryLock();//不断的请求锁，如果请求不到，等一秒再请求
                    break;
                } catch (Exception e) {
                    System.out.print("lock is exist ......");
                    sleep(1000);
                }
            }
            String str = " hello word!!!!\n";//需要写入的内容

            fout.write(str.getBytes());//将需要写入的内容写入文件

            flout.release();
            fcout.close();
            fout.close();*/

        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.print("file no find ...");
        } finally {
            if (fcout != null) {
                try {
                    fcout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fcout = null;
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fout = null;
                }
            }
        }
    }

}
