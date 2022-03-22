###1. 登录阿里云Docker Registry
```shell
docker login --username=********@163.com registry.cn-hangzhou.aliyuncs.com
```
用于登录的用户名为阿里云账号全名，密码为开通服务时设置的密码。

您可以在访问凭证页面修改凭证密码。

###2. 从Registry中拉取镜像
```shell
docker pull registry.cn-hangzhou.aliyuncs.com/f-boot/boot:[镜像版本号]
```
###3. 将镜像推送到Registry
```shell
docker login --username=********@163.com registry.cn-hangzhou.aliyuncs.com
docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/f-boot/****:[镜像版本号]
docker push registry.cn-hangzhou.aliyuncs.com/f-boot/boot:[镜像版本号]
```
   请根据实际镜像信息替换示例中的[ImageId]和[镜像版本号]参数。

###4. 选择合适的镜像仓库地址
   从ECS推送镜像时，可以选择使用镜像仓库内网地址。推送速度将得到提升并且将不会损耗您的公网流量。

如果您使用的机器位于VPC网络，请使用 registry-vpc.cn-hangzhou.aliyuncs.com 作为Registry的域名登录。

###5. 示例
   使用"docker tag"命令重命名镜像，并将它通过专有网络地址推送至Registry。

```shell
docker images
REPOSITORY                                                 TAG         IMAGE ID       CREATED         SIZE
registry.cn-hangzhou.aliyuncs.com/f-boot/gateway           1.0.0       0ed43a305305   9 minutes ago   320MB
```
使用 "docker push" 命令将该镜像推送至远程。
```shell
docker push registry.cn-hangzhou.aliyuncs.com/f-boot/gateway:1.0.0
```

###6.执行构建
```shell
gradle jibDockerBuild
```
