package com.springmvc.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by qudi on 2018/2/19.
 */
public class HttpUtils {
    public static void writeJson(HttpServletResponse response,Object obj) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(GsonUtils.toJSONString(obj));
        writer.flush();
        writer.close();
    }
}
