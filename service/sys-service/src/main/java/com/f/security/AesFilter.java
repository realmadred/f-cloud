/*
 * Copyright [2021] [liufeng]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.f.security;

import com.f.config.SysProperties;
import com.f.dto.DataDto;
import com.f.utils.AesUtils;
import com.f.utils.Json;
import com.f.utils.ServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * aes 解密过滤器
 *
 * @author liuf
 * @date 2022年1月18日
 */
@Slf4j
@RequiredArgsConstructor
public class AesFilter implements Filter {

    private final SysProperties sysProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (HttpMethod.POST.matches(httpRequest.getMethod()) || HttpMethod.PUT.matches(httpRequest.getMethod())) {
            if (sysProperties.getAuth().getAesUris().contains(httpRequest.getRequestURI())) {
                RequestWrapper requestWrapper = (RequestWrapper) request;
                final DataDto tokenDataDto = Json.parse(new String(requestWrapper.getBody()), DataDto.class);
                requestWrapper.setBody(AesUtils.decrypt(tokenDataDto.getData(), ServiceUtils.getSysUser().getKey()).getBytes(StandardCharsets.UTF_8));
                chain.doFilter(requestWrapper, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

}