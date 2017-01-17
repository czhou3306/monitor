/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

import com.qiangungun.monitor.common.model.KeyConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author czhou3306@gmail.com
 * @version $Id: LineFromEndReader.java, v0.1 2016年12月15日 上午11:28:18 czhou3306@gmail.com Exp $
 */
public class LineFromEndReader {

    private static final Logger logger = LoggerFactory.getLogger(LineFromEndReader.class);

    private RandomAccessFile accessFile = null;

    private MappedByteBuffer mappedByteBuffer = null;

    private long pointer;

    public LineFromEndReader(String fileName) {
        try {
            accessFile = new RandomAccessFile(fileName, "r");
            mapped();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LineFromEndReader(File file) {
        try {
            accessFile = new RandomAccessFile(file, "r");
            mapped();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mapped() throws IOException {
        long length = accessFile.length();

        pointer = length - 1;
        mappedByteBuffer = accessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
    }


    public void close() {
        try {
            if (mappedByteBuffer != null) {
                unmap(mappedByteBuffer);
            }

            if (accessFile != null) {
                accessFile.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void unmap(final MappedByteBuffer mappedByteBuffer) {
        try {
            mappedByteBuffer.force();
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                @SuppressWarnings("restriction")
                public Object run() {
                    try {
                        Cleaner cleaner = ((DirectBuffer) mappedByteBuffer).cleaner();
                        cleaner.clean();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });

        } catch (Exception e) {
            logger.error("clean MappedByteBuffer error", e);
        }
    }

    public String readLineHaveContent() {
        String line = StringUtils.EMPTY;

        while (StringUtils.equals(StringUtils.EMPTY, line)) {
            line = readLine();
        }

        return line;
    }

    private String readLine() {
        if (pointer < 0) {
            return null;
        }

        List<Byte> list = new ArrayList<>(1024);
        try {
            while (pointer >= 0) {
                byte c = mappedByteBuffer.get((int) pointer);

                if (c == '\n' || c == '\r') {
                    pointer--;
                    break;
                }

                list.add(new Byte(c));
                pointer--;
            }
            return reverseToString(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String reverseToString(List<Byte> list) {
        if (CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }

        try {
            int length = list.size();
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = list.get(length - 1 - i).byteValue();
            }
            return new String(data, KeyConstant.UTF8);
        } catch (UnsupportedEncodingException e) {
            logger.error("读取编码错误", e);
            return null;
        }
    }

    public static void main(String args[]) {
        LineFromEndReader reader = new LineFromEndReader("E:\\opt\\logs\\mobile\\biz.log");
        long beginTime = System.nanoTime();
        long count = 0;
        try {

            String string = null;
            while ((string = reader.readLineHaveContent()) != null) {
                count++;
                //System.out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行时间" + (System.nanoTime() - beginTime) / 1000000 + "ms");
            System.out.println("执行条数" + count);
        }
    }
}
