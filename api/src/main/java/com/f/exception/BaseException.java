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

import com.f.enums.ResultEnum;
import lombok.Getter;

/**
 * 框架公共异常
 * 不会有堆栈信息
 *
 * @author liuf
 * @date 2021/12/3 13:50
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 状态码
     */
    private final int code;

    public BaseException(final int code, final String message) {
        super(message, null, false, false);
        this.code = code;
    }

    public static BaseException of(final int code, final String message) {
        return new BaseException(code, message);
    }

    public static BaseException of(final String message) {
        return new BaseException(ResultEnum.ERROR.getCode(), message);
    }

    public static BaseException of(final ResultEnum resultEnum) {
        return new BaseException(resultEnum.getCode(), resultEnum.getMsg());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
