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
package com.f.exception;

import com.f.enums.ResultEnum;

/**
 * 服务异常
 *
 * @author liuf
 * @date 2021/12/3 14:00
 */
public class ServiceException extends BaseException {
    public ServiceException(final int code, final String message) {
        super(code, message);
    }

    public static ServiceException of(final int code, final String message) {
        return new ServiceException(code, message);
    }

    public static ServiceException of(final String message) {
        return new ServiceException(ResultEnum.ERROR.getCode(), message);
    }

    public static ServiceException of(final ResultEnum resultEnum) {
        return new ServiceException(resultEnum.getCode(), resultEnum.getMsg());
    }
}
