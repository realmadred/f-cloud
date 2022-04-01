> 关于在k8s-v1.20以上版本使用nfs作为storageclass出现selfLink was empty, can‘t make reference

在使用nfs创建storageclass 实现存储的动态加载
分别创建 rbac、nfs-deployment、nfs-storageclass之后都正常运行
但在创建pvc时一直处于pending状态

- 查看日志\
  E0401 08:31:50.204696 1 controller.go:766] Unexpected error getting claim reference to claim "default/test-chaim": selfLink was empty, can't make reference

>selfLink was empty 在k8s集群 v1.20之前都存在，在v1.20之后被删除，需要在/etc/kubernetes/manifests/kube-apiserver.yaml 添加参数
>增加 - --feature-gates=RemoveSelfLink=false

```yaml
spec:
  containers:
  - command:
    - kube-apiserver
    - --feature-gates=RemoveSelfLink=false
```
- 添加之后使用kubeadm部署的集群会自动加载部署pod

```
kubeadm安装的apiserver是Static Pod，它的配置文件被修改后，立即生效。
Kubelet 会监听该文件的变化，当您修改了 /etc/kubenetes/manifest/kube-apiserver.yaml 文件之后，kubelet 将自动终止原有的 kube-apiserver-{nodename} 的 Pod，并自动创建一个使用了新配置参数的 Pod 作为替代。
如果您有多个 Kubernetes Master 节点，您需要在每一个 Master 节点上都修改该文件，并使各节点上的参数保持一致。
```
- 这里需注意如果api-server启动失败 需重新在执行一遍

``` shell
kubectl apply -f /etc/kubernetes/manifests/kube-apiserver.yaml
```
