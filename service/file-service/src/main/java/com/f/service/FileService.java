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
package com.f.service;

import com.f.dto.GetObjectDto;
import com.f.dto.PreUrlGetObjectDto;
import com.f.dto.PreUrlPutObjectDto;
import com.f.dto.PutObjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件服务
 *
 * @author liuf
 * @date 2022/2/22 14:17
 */
public interface FileService {

    /**
     * 上传
     *
     * @param putObjectDto 上传对象
     * @return 路径
     * @date 2022年2月22日
     */
    String putObject(PutObjectDto putObjectDto);

    /**
     * 上传
     *
     * @param putObjectDto 上传对象
     * @param file 上传文件
     * @return 路径
     * @date 2022年2月22日
     */
    String putMultipartFile(PutObjectDto putObjectDto, MultipartFile file);

    /**
     * 预上传url生成
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    String getPreSignedPutObjectUrl(PreUrlPutObjectDto preUrlObjectDto);

    /**
     * 预get url生成
     *
     * @param preUrlGetObjectDto dto
     * @return url
     */
    String getPreSignedGetObjectUrl(PreUrlGetObjectDto preUrlGetObjectDto);

    /**
     * 预上传url生成 批量
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    List<String> getPreSignedPutObjectUrlList(PreUrlPutObjectDto preUrlObjectDto);

    /**
     * 下载
     *
     * @param getObjectDto 对象
     * @return 文件流
     * @date 2022年2月22日
     */
    InputStream getObject(GetObjectDto getObjectDto);
}
