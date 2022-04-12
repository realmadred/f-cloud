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
package com.f.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * json 工具类
 * 注意 需要初始化jsonMapper，全局唯一线程安全
 *
 * @author liuf
 * @date 2021/12/17 11:15
 */
@Slf4j
public final class Json {

    private final static ObjectMapper OBJECT_MAPPER = ApplicationContextUtils.getBean(ObjectMapper.class);

    private static final Json.MapTypeReference MAP_TYPE = new Json.MapTypeReference();

    private static final Json.ListTypeReference LIST_TYPE = new Json.ListTypeReference();

    /**
     * 解析json
     *
     * @param json      json字符串
     * @param valueType 类型
     * @param <T>       返回类型
     * @return 对象
     */
    @SneakyThrows
    public static <T> T parse(String json, Class<T> valueType) {
        return OBJECT_MAPPER.readValue(json, valueType);
    }

    /**
     * 解析json
     *
     * @param json         json字符串
     * @param valueTypeRef 类型
     * @param <T>          返回类型
     * @return 对象
     */
    @SneakyThrows
    public static <T> T parse(String json, TypeReference<T> valueTypeRef) {
        return OBJECT_MAPPER.readValue(json, valueTypeRef);
    }

    /**
     * 解析json
     *
     * @param json      json字符串
     * @param valueType 类型
     * @param <T>       返回类型
     * @return 对象
     */
    @SneakyThrows
    public static <T> T parse(String json, JavaType valueType) {
        return OBJECT_MAPPER.readValue(json, valueType);
    }

    /**
     * 解析json
     *
     * @param json json字符串
     * @return 对象
     */
    @SneakyThrows
    public static Map<String, Object> parse(String json) {
        return OBJECT_MAPPER.readValue(json, MAP_TYPE);
    }

    /**
     * 解析json
     *
     * @param json json字符串
     * @return 对象
     */
    @SneakyThrows
    public static JsonNode parseTree(String json) {
        return OBJECT_MAPPER.readTree(json);
    }

    /**
     * 解析json
     *
     * @param json json字符串
     * @return 对象
     */
    @SneakyThrows
    public static List<Object> parseList(String json) {
        return OBJECT_MAPPER.readValue(json, LIST_TYPE);
    }

    /**
     * 对象转json
     *
     * @param value json对象
     * @return json
     */
    @SneakyThrows
    public static String json(Object value) {
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    /**
     * 对象转json bytes
     *
     * @param value json对象
     * @return json
     */
    @SneakyThrows
    public static byte[] jsonBytes(Object value) {
        return OBJECT_MAPPER.writeValueAsBytes(value);
    }

    /**
     * 创建ObjectNode
     *
     * @return ObjectNode
     */
    public static ObjectNode getObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    private static class MapTypeReference extends TypeReference<Map<String, Object>> {

    }

    private static class ListTypeReference extends TypeReference<List<Object>> {

    }

    private Json() {
    }
}
