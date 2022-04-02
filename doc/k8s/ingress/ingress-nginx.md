### 1. 默认安装后`pending`
```shell
[root@kube k8s]# kubectl get service ingress-nginx-controller --namespace=ingress-nginx
NAME                       TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)                      AGE
ingress-nginx-controller   LoadBalancer   10.88.39.184   <pending>     80:32480/TCP,443:31411/TCP   21m
```
> 这将是EXTERNAL-IP领域。如果该字段显示<pending>，这意味着您的 Kubernetes 集群无法配置负载均衡器（通常，这是因为它不支持类型的服务LoadBalancer）。

### 2. 可以使用nodePort方式暴露
```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.2/deploy/static/provider/baremetal/deploy.yaml
```
> 如何使用端口 80 而不是 30000-32767 范围内的随机端口），请参阅[裸机注意事项](https://kubernetes.github.io/ingress-nginx/deploy/baremetal/)。

### 3. 使用本机网络端口
在没有可用的外部负载均衡器但不能使用 NodePorts 的设置中，可以将 Pod 配置ingress-nginx为使用它们运行的主机的网络，而不是专用的网络命名空间。这种方法的好处是 NGINX Ingress 控制器可以将端口 80 和 443 直接绑定到 Kubernetes 节点的网络接口，而无需 NodePort 服务强加的额外网络转换。
>这种方法不利用任何服务对象来公开 NGINX 入口控制器。如果ingress-nginx目标集群中存在Service，建议删除。
```yaml
template:
  spec:
    hostNetwork: true
```
>启用此选项会将每个系统守护程序公开给任何网络接口上的 NGINX Ingress 控制器，包括主机的环回。请仔细评估这可能对您的系统安全产生的影响。

- 这种部署方法的一个主要限制是每个集群节点上只能调度一个 NGINX Ingress 控制器 Pod，因为在同一个网络接口上多次绑定同一个端口在技术上是不可能的。由于这种情况而无法调度的 Pod 会因以下事件而失败：
- 确保仅创建可调度 Pod 的一种方法是将 NGINX Ingress 控制器部署为DaemonSet而不是传统的 Deployment。
```yaml
# 1.更改DaemonSet
apiVersion: apps/v1
kind: DaemonSet
# 2.更改hostNetwork
template:
  spec:
    hostNetwork: true
```

- 因为没有服务在使用主机网络的配置中公开 NGINX Ingress 控制器，所以--publish-service标准云设置中使用的默认标志不适用，并且所有 Ingress 对象的状态保持空白。

- 相反，由于裸机节点通常没有 ExternalIP，因此必须启用该--report-node-internal-ip-address标志，它将所有 Ingress 对象的状态设置为运行 NGINX Ingress 控制器的所有节点的内部 IP 地址。