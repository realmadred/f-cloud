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

package com.f.controller;

import com.f.entity.CellOrderItem;
import com.f.service.CellOrderItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细  接口
 *
 * @author liuf
 * @date 2022-04-08
 */
@RestController
@RequestMapping("/cellOrderItem")
@RequiredArgsConstructor
@Getter
public class CellOrderItemController extends BaseController<CellOrderItem, CellOrderItemService> {

    private final CellOrderItemService service;

}

