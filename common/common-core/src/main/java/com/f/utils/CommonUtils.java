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

import com.f.base.BaseEntity;
import com.f.base.BaseTreeDto;
import com.f.base.BaseTreeEntity;
import com.f.base.TreeDto;
import com.f.constant.Constant;
import com.f.thread.CommonThreadUtils;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

/**
 * 通用工具类
 *
 * @author liuf
 * @date 2021/12/6 16:53
 */
@Slf4j
public final class CommonUtils {

    public static final byte MAX_LEVEL = 20;

    /**
     * ByteBuffer -> byte[]
     *
     * @param buffer ByteBuffer
     * @return byte[]
     */
    public static byte[] getBytes(final ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (remaining == 0) {
            return Constant.EMPTY_BYTES;
        } else {
            byte[] bytes = new byte[remaining];
            buffer.get(bytes);
            return bytes;
        }
    }

    /**
     * 多线程并发执行
     *
     * @param concurrency  并发量
     * @param runnable     并发执行业务
     * @param afterExecute 最后执行业务
     */
    @SneakyThrows
    public static void concurrencyExecution(final int concurrency, final @NonNull Runnable runnable, final Runnable afterExecute) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch all = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            CommonThreadUtils.execute(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    log.error("latchExecution", e);
                }
                runnable.run();
                all.countDown();
            });
        }
        start.countDown();
        all.await();
        if (afterExecute != null) {
            afterExecute.run();
        }
    }

    /**
     * 获取实体的id
     *
     * @param entity 实体
     * @return id
     */
    public static Long getId(BaseEntity entity) {
        return LambdaUtils.getOrElse(entity, BaseEntity::getId, null);
    }

    /**
     * 列表转化为树结构
     *
     * @param <T>    泛型树对象
     * @param list   实体列表
     * @param rootId 根节点id
     * @return 树结构
     */
    public static <T extends BaseTreeEntity> List<TreeDto<T>> buildTree(List<T> list, Long rootId) {
        List<TreeDto<T>> tree = new ArrayList<>();
        for (T node : list) {
            TreeDto<T> treeDto = new TreeDto<>();
            treeDto.setNode(node);
            if (Objects.equals(rootId, node.getPid())) {
                tree.add(treeDto);
            }
            // 寻找下级节点
            for (T children : list) {
                if (Objects.equals(children.getPid(), node.getId())) {
                    TreeDto<T> childrenDto = new TreeDto<>();
                    childrenDto.setNode(children);
                    if (treeDto.getChildren() == null) {
                        treeDto.setChildren(new ArrayList<>());
                    }
                    treeDto.getChildren().add(childrenDto);
                }
            }
        }
        return tree;
    }

    /**
     * 列表转化为树结构
     *
     * @param <T>    泛型树对象
     * @param list   实体列表
     * @param rootId 根节点id
     * @return 树结构
     */
    public static <T extends BaseTreeDto<T>> List<T> buildTreeDto(List<T> list, Long rootId) {
        List<T> result = new ArrayList<>();
        for (T parent : list) {
            if (Objects.equals(rootId, parent.getPid())) {
                result.add(parent);
            }
            // 寻找下级节点
            for (T children : list) {
                if (Objects.equals(children.getPid(), parent.getId())) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(children);
                }
            }
        }
        return result;
    }

    /**
     * 递归 树结构 转 列表
     * 最大深度 ${MAX_LEVEL}
     *
     * @param <T>      泛型树对象
     * @param tree     树
     * @param consumer 处理节点回调
     * @param level    递归的层级
     * @return 列表
     */
    private static <T extends BaseTreeDto<T>> List<T> recursiveFlatTree(List<T> tree, BiConsumer<T, Integer> consumer, int level) {
        log.info("执行层级:{}", level);
        if (CollectionUtils.isEmpty(tree) || level > MAX_LEVEL) {
            return new ArrayList<>(0);
        }
        List<T> resultList = new ArrayList<>(tree.size() << 2);
        for (T treeDto : tree) {
            if (treeDto == null) {
                continue;
            }

            // 回调自定义处理函数
            if (consumer != null) {
                consumer.accept(treeDto, level);
            }

            List<T> children = treeDto.getChildren();
            resultList.add(treeDto);
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            // 递归处理子节点
            resultList.addAll(recursiveFlatTree(children, consumer, level + 1));
        }
        return resultList;
    }

    /**
     * 递归 树结构 转 列表
     * 最大深度 MAX_LEVEL
     *
     * @param <T>      泛型树对象
     * @param tree     树
     * @param consumer 处理节点回调
     * @return 列表
     */
    public static <T extends BaseTreeDto<T>> List<T> flatTree(List<T> tree, BiConsumer<T, Integer> consumer) {
        return recursiveFlatTree(tree, consumer, 1);
    }

    /**
     * 递归 树结构 转 列表
     * 最大深度 MAX_LEVEL
     *
     * @param <T>  泛型树对象
     * @param tree 树
     * @return 列表
     */
    public static <T extends BaseTreeDto<T>> List<T> flatTree(List<T> tree) {
        return flatTree(tree, null);
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    private CommonUtils() {

    }
}
