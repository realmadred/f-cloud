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
import com.f.dto.file.GetObjectDto;
import com.f.dto.file.PreUrlObjectDto;
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
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
            final PreUrlObjectDto preUrlObjectDto = new PreUrlObjectDto();
            preUrlObjectDto.setBucket("image");
            preUrlObjectDto.setSuffix(".jpg");
            preUrlObjectDto.setExpiry((int) TimeUnit.MINUTES.toSeconds(5));
            final String signedObjectUrl = getPreSignedObjectUrl(preUrlObjectDto);
            log.info("init signedObjectUrl:{}", signedObjectUrl);
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

    @NotNull
    private String getName(String suffix) {
        return IdUtils.uuid() + suffix;
    }

    @SneakyThrows
    @Override
    public String getPreSignedObjectUrl(PreUrlObjectDto preUrlObjectDto) {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(preUrlObjectDto.getBucket())
                .object(getName(preUrlObjectDto.getSuffix()))
                .expiry(preUrlObjectDto.getExpiry())
                .method(Method.POST)
                .build());
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
