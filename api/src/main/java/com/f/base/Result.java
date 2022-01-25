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

package com.f.base;

import com.f.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果
 *
 * @param <T> 任意类型data
 * @author liuf
 * @date 2021/12/3 11:43
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2744720363609436887L;

    /**
     * 状态码
     * 200 表示请求成功
     * 500 异常
     * 其它看提示消息
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功响应
     *
     * @param data 数据
     * @param <T>  泛型
     * @return result
     */
    public static <T> Result<T> success(final T data) {
        final Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    /**
     * 成功响应
     *
     * @param <T> 泛型
     * @return result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败提示
     *
     * @param code 状态码
     * @param msg  消息提示
     * @param <T>  泛型
     * @return result
     */
    public static <T> Result<T> fail(final int code, final String msg) {
        final Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败提示
     *
     * @param msg 消息提示
     * @param <T> 泛型
     * @return result
     */
    public static <T> Result<T> fail(final String msg) {
        final Result<T> result = new Result<>();
        result.setCode(ResultEnum.COMMON.getCode());
        result.setMsg(msg);
        return result;
    }


    /**
     * 失败提示
     *
     * @param resultEnum 结果枚举
     * @param <T>        泛型
     * @return result
     */
    public static <T> Result<T> fail(final ResultEnum resultEnum) {
        return fail(resultEnum.getCode(), resultEnum.getMsg());
    }

}
