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

import com.f.dto.file.GetObjectDto;
import com.f.dto.file.PreUrlGetObjectDto;
import com.f.dto.file.PreUrlPutObjectDto;
import com.f.dto.file.PutObjectDto;
import com.f.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件服务
 *
 * @author liuf
 * @date 2022/2/22 14:18
 */
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    /**
     * 上传
     *
     * @param putObjectDto 上传对象
     * @return 路径
     * @date 2022年2月23日
     */
    @PostMapping("/putMultipartFile")
    public String putMultipartFile(PutObjectDto putObjectDto, @RequestParam("file") MultipartFile file) {
        return fileService.putMultipartFile(putObjectDto, file);
    }

    /**
     * 上传
     *
     * @param putObjectDto 上传对象
     * @return 路径
     * @date 2022年2月23日
     */
    @PostMapping("/putObject")
    public String putObject(PutObjectDto putObjectDto) {
        return fileService.putObject(putObjectDto);
    }

    /**
     * 预上传url生成
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedObjectUrl")
    public String getPreSignedObjectUrl(PreUrlPutObjectDto preUrlObjectDto) {
        return fileService.getPreSignedPutObjectUrl(preUrlObjectDto);
    }

    /**
     * 预下载url生成
     *
     * @param preUrlGetObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedGetObjectUrl")
    public String getPreSignedGetObjectUrl(PreUrlGetObjectDto preUrlGetObjectDto) {
        return fileService.getPreSignedGetObjectUrl(preUrlGetObjectDto);
    }

    /**
     * 预上传url生成 批量
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedObjectUrlList")
    public List<String> getPreSignedObjectUrlList(PreUrlPutObjectDto preUrlObjectDto) {
        return fileService.getPreSignedObjectUrlList(preUrlObjectDto);
    }

    /**
     * 下载
     *
     * @param getObjectDto 对象
     * @return 文件流
     * @date 2022年2月23日
     */
    @GetMapping("/getObject")
    public InputStream getObject(@ModelAttribute GetObjectDto getObjectDto) {
        return fileService.getObject(getObjectDto);
    }
}
