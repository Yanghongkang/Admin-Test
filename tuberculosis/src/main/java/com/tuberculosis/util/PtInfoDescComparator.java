package com.tuberculosis.util;

import com.tuberculosis.entity.PtInfo;

import java.util.Comparator;

/**
 * Created by Li Shaoqing
 * on 16/5/16.
 */
public class PtInfoDescComparator implements Comparator<PtInfo> {

    @Override
    public int compare(PtInfo p1, PtInfo p2) {
        Long l1 = p1.getId();
        Long l2 = p2.getId();
        return (l1 < l2) ? 1 : ((l1 .equals(l2)) ? 0 : -1);
    }
}
