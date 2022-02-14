/*
 * Copyright [2022] [liufeng]
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
import com.f.constant.Constant;
import com.f.entity.sys.SysLog;
import com.f.enums.sys.SysLogTypeEnum;
import com.f.log.LogDisruptor;
import com.f.service.sys.SysMenuService;
import com.f.thread.ThreadLocalContext;
import com.f.utils.IdUtils;
import com.f.utils.Json;
import com.f.utils.ServiceUtils;
import com.f.vo.sys.SysUserVo;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 接口权限拦截器
 *
 * @author liuf
 * @date 2022/1/13 20:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthInterceptor implements HandlerInterceptor {

    private static final String PAGE = "page";
    private final SysMenuService sysMenuService;
    private final SysProperties sysProperties;

    /**
     * 权限前置拦截器
     *
     * @param request  请求
     * @param response 响应
     * @param handler  当前方法
     * @return 是否通过
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        long userId = ServiceUtils.getUserId();
        if (userId == Constant.ADMIN_ID) {
            // admin 直接放行
            return true;
        }

        String method = request.getMethod();
        String uri = request.getRequestURI();
        if (HttpMethod.GET.matches(method) || uri.contains(PAGE) || sysProperties.getAuth().getExcludePerms().contains(uri)) {
            return true;
        }

        Set<String> perms = sysMenuService.selectPermsByUserId(userId);
        if (perms.contains(uri + Constant.SLASH + method.toLowerCase())) {
            return true;
        }
        ServiceUtils.responseForbidden(response);
        return false;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        final String exception = (String) ThreadLocalContext.remove(Constant.EX);
        final String requestUri = request.getRequestURI();
        if (sysProperties.getLog().getExcludeUris().contains(requestUri)) {
            return;
        }
        try {
            SysUserVo sysUser = ServiceUtils.getSysUser();
            HandlerMethod method = (HandlerMethod) handler;
            SysLog sysLog = new SysLog()
                    .setId(IdUtils.id())
                    .setCreateId(sysUser.getId())
                    .setCreateName(sysUser.getName())
                    .setCreateTime(LocalDateTime.now())
                    .setClassName(method.getBeanType().getSimpleName())
                    .setMethodName(method.getMethod().getName())
                    .setRequestUri(requestUri)
                    .setType(exception == null ? SysLogTypeEnum.INFO.getType() : SysLogTypeEnum.ERROR.getType())
                    .setParams(getParams(request))
                    .setRequestIp(ServiceUtils.getIp(request))
                    .setErrorDetail(exception);
            LogDisruptor.publishEvent(sysLog);
        } catch (Exception e) {
            log.error("log error", e);
        }
    }

    /**
     * 获取请求参数
     *
     * @param request 请求
     * @return json
     */
    @SneakyThrows
    private String getParams(HttpServletRequest request) {
        if (HttpMethod.GET.matches(request.getMethod())) {
            return Json.json(request.getParameterMap());
        } else {
            if (sysProperties.getLog().getExcludeParams().contains(request.getRequestURI())) {
                return Constant.EMPTY;
            }
            return Joiner.on(Constant.EMPTY).join(IOUtils.readLines(request.getInputStream(), StandardCharsets.UTF_8));
        }
    }
}
