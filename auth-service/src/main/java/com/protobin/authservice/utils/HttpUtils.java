package com.protobin.authservice.utils;

import com.protobin.authservice.entity.RequestInfo;
import jakarta.servlet.http.HttpServletRequest;

public abstract class HttpUtils {

    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public static RequestInfo parseRequestInfo(HttpServletRequest request) {
        var requestInfo = new RequestInfo();

        requestInfo.setIpAddress(getRequestIP(request));
        requestInfo.setDeviceInfo(getDeviceInfo(request));

        return requestInfo;
    }

    private static String getRequestIP(HttpServletRequest request) {
        for (String header: IP_HEADERS)
            if (request.getHeader(header) != null) {
                return header.split(",")[0];
            }

        return request.getRemoteAddr();
    }

    private static String getDeviceInfo(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
