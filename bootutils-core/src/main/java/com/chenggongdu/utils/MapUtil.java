package com.chenggongdu.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Map工具类
 *
 * @author chenggongdu
 */
public class MapUtil extends cn.hutool.core.map.MapUtil {

    /**
     * 将Object二维数组转换为Map<String,Object>格式
     *
     * @param os object 二维数组
     * @return {@link Map<>}
     */
    public static Map<String, Object> toMap(Object[][] os) {
        Map<String, Object> m = new LinkedHashMap<>();
        for (Object[] o : os) {
            m.put(o[0] == null ? null : o[0].toString(), o[1]);
        }
        return m;
    }

    /**
     * 将Object二维数组转换为Map<String,String>格式
     *
     * @param os object 二维数组
     * @return {@link Map<>}
     */
    public static Map<String, String> toMapString(Object[][] os) {
        Map<String, String> m = new LinkedHashMap<>();
        for (Object[] o : os) {
            m.put(o[0] == null ? null : o[0].toString(), o[1] == null ? null : o[1].toString());
        }
        return m;
    }
}
