package com.f.security;

import com.f.constant.Constant;
import com.google.common.base.Joiner;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 可以重复获取请求数据request
 *
 * @author liufeng
 * @date 2022年1月14日
 */
@Getter
@Setter
public class RequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {

    private byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return inputStream.available() > 0;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    @SneakyThrows
    private String getBodyString(ServletRequest request) {
        return Joiner.on(Constant.EMPTY).join(IOUtils.readLines(request.getInputStream(), StandardCharsets.UTF_8));
    }
}