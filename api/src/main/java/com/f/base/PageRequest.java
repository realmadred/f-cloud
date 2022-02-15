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
package com.f.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页请求
 *
 * @param <T> 实体对象
 * @author liuf
 * @date 2021/12/3 11:35
 */
@Data
public class PageRequest<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -6806186760886845572L;

    private T entity;

    /**
     * 每页显示条数，默认 10
     */
    @Range(min = 1, max = 50, message = "size: {org.hibernate.validator.constraints.Range.message}")
    private int size = 10;

    /**
     * 当前页
     */
    @Range(min = 1, max = 100, message = "page: {org.hibernate.validator.constraints.Range.message}")
    private int page = 1;

    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();

    /**
     * 重写排序方法,防止sql注入
     * @return 排序数据
     */
    public List<OrderItem> getOrders() {
        return orders.stream().filter(o -> o.getColumn() != null && !o.getColumn().contains(" "))
                .collect(Collectors.toList());
    }

    /**
     * 是否进行 count 查询
     */
    private boolean searchCount = false;

    /**
     * 转换成mybatis plus page对象
     *
     * @return page
     */
    public final Page<T> toPlusPage() {
        PageDTO<T> pageDTO = new PageDTO<>(page, size, searchCount);
        pageDTO.setOrders(getOrders());
        return pageDTO;
    }
}
