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

import com.f.base.Result;
import com.f.dto.GetObjectDto;
import com.f.dto.PreUrlGetObjectDto;
import com.f.dto.PreUrlPutObjectDto;
import com.f.dto.PutObjectDto;
import com.f.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
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
    @PutMapping("/putMultipartFile")
    public Result<String> putMultipartFile(PutObjectDto putObjectDto, @RequestParam("file") MultipartFile file) {
        return Result.success(fileService.putMultipartFile(putObjectDto, file));
    }

    /**
     * 上传
     *
     * @param putObjectDto 上传对象
     * @return 路径
     * @date 2022年2月23日
     */
    @PutMapping("/putObject")
    public Result<String> putObject(PutObjectDto putObjectDto) {
        return Result.success(fileService.putObject(putObjectDto));
    }

    /**
     * 预上传url生成
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedPutObjectUrl")
    public Result<String> getPreSignedPutObjectUrl(PreUrlPutObjectDto preUrlObjectDto) {
        return Result.success(fileService.getPreSignedPutObjectUrl(preUrlObjectDto));
    }

    /**
     * 预下载url生成
     *
     * @param preUrlGetObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedGetObjectUrl")
    public Result<String> getPreSignedGetObjectUrl(PreUrlGetObjectDto preUrlGetObjectDto) {
        return Result.success(fileService.getPreSignedGetObjectUrl(preUrlGetObjectDto));
    }

    /**
     * 预上传url生成 批量
     *
     * @param preUrlObjectDto dto
     * @return url
     */
    @GetMapping("/getPreSignedPutObjectUrlList")
    public Result<List<String>> getPreSignedPutObjectUrlList(PreUrlPutObjectDto preUrlObjectDto) {
        return Result.success(fileService.getPreSignedPutObjectUrlList(preUrlObjectDto));
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
