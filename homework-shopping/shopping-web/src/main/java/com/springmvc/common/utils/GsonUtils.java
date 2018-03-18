package com.springmvc.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.List;

/**
 * Created by qudi on 2018/2/19.
 */
public class GsonUtils {
    private static final SerializerFeature[] features = {
            SerializerFeature.DisableCircularReferenceDetect,//打开循环引用检测
            SerializerFeature.WriteNullListAsEmpty,//list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null，输出为""，而不是null
            //SerializerFeature.WriteNullNumberAsZero,//数值字段如果为null，输出为0，而不是null
            SerializerFeature.SkipTransientField,//跳过transient字段
            SerializerFeature.WriteDateUseDateFormat,//默认使用系统默认格式日期格式化
            //SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteMapNullValue,//输出空值字段
    };

    public static String toJSONString(Object obj) {
        return JSONObject.toJSONString(obj, features);
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSONObject.parseArray(text, clazz);
    }

    public static <T> T toJavaObject(JSON json, Class<T> clazz) {
        return TypeUtils.cast(json, clazz, ParserConfig.getGlobalInstance());
    }
}
