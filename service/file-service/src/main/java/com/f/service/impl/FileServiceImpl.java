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

import com.f.config.FileProperties;
import com.f.constant.Constant;
import com.f.dto.file.GetObjectDto;
import com.f.dto.file.PreUrlGetObjectDto;
import com.f.dto.file.PreUrlPutObjectDto;
import com.f.dto.file.PutObjectDto;
import com.f.service.FileService;
import com.f.utils.IdUtils;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 文件服务实现
 *
 * @author liuf
 * @date 2022/2/22 14:18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileProperties fileProperties;

    private MinioClient minioClient;

    @PostConstruct
    private synchronized void init() {
        if (minioClient == null) {
            minioClient = MinioClient.builder()
                    .endpoint(Objects.requireNonNull(HttpUrl.parse(fileProperties.getMinIoUrl())))
                    .credentials(fileProperties.getAccessKey(), fileProperties.getAccessSecret())
                    .region(fileProperties.getRegion())
                    .build();
            log.info("init minioClient");
        }
    }

    @SneakyThrows
    @Override
    public String putObject(PutObjectDto putObjectDto) {
        final ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder().bucket(putObjectDto.getBucket())
                .object(getName(putObjectDto.getSuffix()))
                .stream(putObjectDto.getStream(), putObjectDto.getObjectSize(), putObjectDto.getPartSize())
                .build());
        log.info("result:{}", response);
        return response.object();
    }

    @SneakyThrows
    @Override
    public String putMultipartFile(PutObjectDto putObjectDto, MultipartFile file) {
        putObjectDto.setStream(file.getInputStream());
        putObjectDto.setSuffix(Constant.DOT + StringUtils.substringAfterLast(Constant.DOT, file.getOriginalFilename()));
        return putObject(putObjectDto);
    }

    @NotNull
    private String getName(String suffix) {
        return IdUtils.uuid() + suffix;
    }

    @SneakyThrows
    @Override
    public String getPreSignedPutObjectUrl(PreUrlPutObjectDto preUrlObjectDto) {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(preUrlObjectDto.getBucket())
                .object(getName(preUrlObjectDto.getSuffix()))
                .expiry(preUrlObjectDto.getExpiry())
                .method(Method.POST)
                .build());
    }

    @SneakyThrows
    @Override
    public String getPreSignedGetObjectUrl(PreUrlGetObjectDto preUrlGetObjectDto) {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(preUrlGetObjectDto.getBucket())
                .object(preUrlGetObjectDto.getName())
                .expiry(preUrlGetObjectDto.getExpiry())
                .method(Method.GET)
                .build());
    }

    @Override
    public List<String> getPreSignedObjectUrlList(PreUrlPutObjectDto preUrlObjectDto) {
        final int size = preUrlObjectDto.getSize();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(getPreSignedPutObjectUrl(preUrlObjectDto));
        }
        return list;
    }

    @SneakyThrows
    @Override
    public InputStream getObject(GetObjectDto getObjectDto) {
        final GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder()
                .bucket(getObjectDto.getBucket())
                .object(getObjectDto.getName())
                .build());
        log.info("result:{}", response);
        return response;
    }
}
