/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.convert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.qiangungun.monitor.common.model.GatherFile;
import com.qiangungun.monitor.common.model.KeyConstant;
import com.qiangungun.monitor.dal.dataobject.GatherFileDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFileConvert.java, v0.1 2016年11月22日 下午11:16:20 czhou3306@gmail.com Exp $
 */
public class GatherFileConvert {

    public static GatherFile convert(GatherFileDO gatherFileDO) {
        GatherFile gatherFile = new GatherFile();
        gatherFile.setFileId(gatherFileDO.getFileId());
        gatherFile.setFilePath(gatherFileDO.getFilePath());

        List<Integer> positionList = new ArrayList<Integer>();
        String[] positionArray = gatherFileDO.getGroupFieldPosition().split(
            KeyConstant.POSITION_SPLIT);

        for (String position : positionArray) {
            if (StringUtils.isBlank(position)) {
                continue;
            }
            String[] field = StringUtils.split(position, "=");
            //从第1位开始，截掉$符
            gatherFile.putField(new Integer(field[0].substring(1)), field[1]);
            //positionList.add(new Integer(field[0].substring(1)));
        }

        //    gatherFile.setGroupFieldPosition(positionList);
        String regex = gatherFileDO.getSplitRegex();

        gatherFile.setSplitRegex(getSplitChars(regex));
        gatherFile.setSystemName(gatherFileDO.getSystemName());
        gatherFile.setGatherType(gatherFileDO.getGatherType());
        return gatherFile;
    }

    public static List<String> getSplitChars(String regex) {

        List<Integer> position = new LinkedList<Integer>();

        int flag = 0;
        while (flag >= 0) {
            int now = regex.indexOf("\"", flag);
            if (now < 0) {
                break;
            }
            position.add(new Integer(now));
            flag = (now + 1);
        }

        Integer[] positions = position.toArray(new Integer[position.size()]);

        List<String> splitChars = new ArrayList<String>();
        for (int i = 0; i < position.size() / 2; i++) {
            splitChars
                .add(StringUtils.substring(regex, positions[2 * i] + 1, positions[2 * i + 1]));
        }

        return splitChars;
    }

}
