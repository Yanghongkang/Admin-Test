package com.tuberculosis.util;

import java.util.Comparator;

/**
 * Created by Li Shaoqing
 * on 16/5/16.
 */
public class LongDescComparator implements Comparator<Long> {

    @Override
    public int compare(Long l1, Long l2) {
        return (l1 < l2) ? 1 : ((l1 .equals(l2)) ? 0 : -1);
    }
}
