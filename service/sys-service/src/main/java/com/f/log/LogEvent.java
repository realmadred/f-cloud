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
package com.f.log;

import com.f.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * Disruptor log event
 *
 * @author liufeng
 * @date 2022年1月14日
 */
@Data
public class LogEvent implements Serializable {

    private static final long serialVersionUID = -8062406159607135574L;

    private SysLog sysLog;

}
