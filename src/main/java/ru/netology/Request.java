package ru.netology;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Request {
    private final String method;
    private final String path;
    private final String[] parts;
    private final List<NameValuePair> queryParams;

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
    public List<NameValuePair> parseQuery (String queryString) {
        return URLEncodedUtils.parse(path, StandardCharsets.UTF_8);
    }

    public List<String> getQueryParams() {
        List<String> params = new ArrayList<>();
        for(NameValuePair pair : queryParams) {
            params.add(pair.getValue());
        }
        return params;
    }

    public List<String> getQueryParam(String name) {
        List<String> params = new ArrayList<>();
        for(NameValuePair pair : queryParams) {
            if (pair.getName().equals(name)) {
                params.add(pair.getValue());
            }
        }
        return params;
    }
}
