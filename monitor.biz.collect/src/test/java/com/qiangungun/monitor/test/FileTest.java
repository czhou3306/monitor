package com.qiangungun.monitor.test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */

/**
 * 
 * @author chenfan
 * @version $Id: FileTest.java, v 0.1 2012-5-7 ����04:20:03 chenfan Exp $
 */
public class FileTest {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        File file = new File("/opt/logs/mobile/digest.log");
                        RandomAccessFile randomFile = new RandomAccessFile(file, "rw");

                        FileChannel fileChannel = randomFile.getChannel();//打开文件通道

                        FileLock fileLock = fileChannel.lock();//不断的请求锁，如果请求不到，等一秒再请求

                        long filelength = randomFile.length();//获取文件的长度
                        randomFile.seek(filelength);//将文件的读写指针定位到文件的末尾

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        randomFile.write((sdf.format(new Date()) + ",304 INFO [http-bio-8080-exec-2] [LogUtil.java : 31] <uuid=1c46f003ad6b2b688a0002bf>/v1/user/webAppLogin,H,,000000,171ms\n")
                            .getBytes());//将需要写入的内容写入文件
                        fileLock.release();
                        fileChannel.close();
                        randomFile.close();
                        Thread.sleep(1000);
                        System.err.println("continue" + new Date());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
