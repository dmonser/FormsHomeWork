package ru.netology;

import org.apache.hc.core5.net.URLEncodedUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private final String method;
    private final String path;
    private final String[] parts;
    private final Map<String, String> queryParams;

    public String[] getParts() {
        return parts;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Request(String requestLine) {
        parts = requestLine.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Bad request");
        }

        path = parts[1].split("\\?")[0];
        String queryString = parts[1].split("\\?")[1];
        queryParams = parseQuery(queryString);

        method = parts[0];
    }

    public Map<String, String> parseQuery (String queryString) {
        String[] params = queryString.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public String getQueryParam(String name) {
        if (queryParams.containsKey(name)) {
            return queryParams.get(name);
        }

        return "Parameter " + name + " not found!";
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}
