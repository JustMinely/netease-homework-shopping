package com.springmvc.common.utils;

import java.util.Collection;

/**
 * 集合工具类
 * Created by qudi on 2018/3/1.
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}