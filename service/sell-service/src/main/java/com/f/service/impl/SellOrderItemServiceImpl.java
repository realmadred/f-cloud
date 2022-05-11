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

package com.f.service.impl;

import com.f.entity.SellOrderItem;
import com.f.mapper.SellOrderItemMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SellOrderItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-05-11
 */
@Service
public class SellOrderItemServiceImpl extends BaseServiceImpl<SellOrderItemMapper, SellOrderItem> implements SellOrderItemService {

}
