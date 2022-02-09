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

package com.f.exception;

import com.f.base.Result;
import com.f.constant.Constant;
import com.f.enums.ResultEnum;
import com.f.thread.ThreadLocalContext;
import com.f.utils.LambdaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;


/**
 * 统一异常处理
 *
 * @author liuf
 * @date 2021/12/3 14:20
 */
@Slf4j
@RestControllerAdvice("com.f")
public class WebExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result<Void> baseExceptionHandler(BaseException e) {
        log.error(e.getMessage(), e.getCode());
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BaseTraceException.class)
    public Result<Void> baseExceptionHandler(BaseTraceException e) {
        log.error(e.getMessage(), e);
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(ResultEnum.ERROR);
    }

    @ExceptionHandler(SecurityException.class)
    public Result<Void> securityException(SecurityException e) {
        log.error(e.getMessage(), e);
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(ResultEnum.UNAUTHORIZED);
    }

    @ExceptionHandler(ValidationException.class)
    public Result<Void> validationExceptionHandler(ValidationException e) {
        log.warn(e.getMessage());
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result<Void> bindException(BindException e) {
        log.warn(e.getMessage());
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), LambdaUtils.getOrElse(e.getBindingResult().getFieldError(),
                FieldError::getDefaultMessage, "bind参数错误"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        ThreadLocalContext.put(Constant.EX, e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), LambdaUtils.getOrElse(e.getBindingResult().getFieldError(),
                FieldError::getDefaultMessage, "参数错误"));
    }

}
