/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import com.qiangungun.monitor.common.model.GatherData;
import com.qiangungun.monitor.common.model.KeyConstant;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.*;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FileGatherDataRepositoryImpl.java, v0.1 2016年11月22日 下午3:25:36 czhou3306@gmail.com Exp $
 */
@Service("gatherDataRepository")
public class FileGatherDataRepositoryImpl implements GatherDataRepository {

    private static final Logger logger   = LoggerFactory
                                             .getLogger(FileGatherDataRepositoryImpl.class);

    private static final String DATA_DIR = "/opt/logs/data/";

    private Map<String, List<GatherData>> groupByFilePath(List<GatherData> gatherDataList) {

        Map<String, List<GatherData>> map = new HashMap<String, List<GatherData>>();
        for (GatherData gatherData : gatherDataList) {
            String filePath = CoreDateUtils.yyyymmdd(gatherData.getOccureDate()) + File.separator
                              + gatherData.getApp() + "." + gatherData.getFileId() + File.separator
                              + gatherData.getIp();
            List<GatherData> tempList = map.get(filePath);
            if (tempList == null) {
                tempList = new ArrayList<GatherData>();
                map.put(filePath, tempList);
            }
            tempList.add(gatherData);
        }

        return map;
    }

    /** 
     * @see com.qiangungun.monitor.repository.GatherDataRepository#batchSave(com.qiangungun.monitor.dal.dataobject.GatherFileDO, java.util.List)
     */
    @Override
    public void batchSave(List<GatherData> gatherDataList) {

        Map<String, List<GatherData>> fileMap = groupByFilePath(gatherDataList);

        Set<String> filePathSet = fileMap.keySet();

        for (String filePath : filePathSet) {
            List<GatherData> dataList = fileMap.get(filePath);
            StringBuilder builder = new StringBuilder();
            for (GatherData data : dataList) {
                builder.append(data.toFileData());
            }
            FileLock fileLock = null;
            FileChannel fileChannel = null;
            RandomAccessFile randomFile = null;
            try {
                File file = new File(DATA_DIR + filePath);

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                randomFile = new RandomAccessFile(file, "rw");

                fileChannel = randomFile.getChannel();//打开文件通道
                while (true) {
                    try {
                        fileLock = fileChannel.tryLock();//不断的请求锁，如果请求不到，等一秒再请求
                        break;
                    } catch (Exception e) {
                        logger.error("获取不到锁", e);
                        Thread.sleep(100);
                    }
                }

                long filelength = randomFile.length();//获取文件的长度
                randomFile.seek(filelength);//将文件的读写指针定位到文件的末尾

                randomFile.write(builder.toString().getBytes(KeyConstant.UTF8));//将需要写入的内容写入文件

            } catch (Exception e) {
                logger.error("保存文件异常filePath=" + filePath, e);
            } finally {
                if (fileLock != null) {
                    try {
                        fileLock.release();
                    } catch (IOException e) {
                        fileLock = null;
                    }
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        fileChannel = null;
                    }

                }
                if (randomFile != null) {
                    try {
                        randomFile.close();
                    } catch (IOException e) {
                        randomFile = null;
                    }

                }
            }
        }

    }
}
