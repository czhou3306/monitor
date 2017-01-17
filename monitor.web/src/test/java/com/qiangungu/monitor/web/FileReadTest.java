/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungu.monitor.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FileReadTest.java, v0.1 2016年12月7日 下午1:20:36 czhou3306@gmail.com Exp $
 */
public class FileReadTest extends TestCase {
    /** 
     *  
     * @param filename 目标文件 
     * @param charset 目标文件的编码格式 
     */
    public static void read(String filename, String charset) {

        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filename, "r");
            long len = rf.length();
            long start = rf.getFilePointer();
            long nextend = start + len - 1;
            String line;
            rf.seek(nextend);
            int c = -1;
            while (nextend > start) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    if (line != null) {
                        System.out.println(line);
                    }
                    nextend--;
                }
                nextend--;
                rf.seek(nextend);
                if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行  
                    // System.out.println(rf.readLine());  
                    System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), charset));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null)
                    rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        read("D:/mobile/biz.log", "UTF-8");
    }
}
