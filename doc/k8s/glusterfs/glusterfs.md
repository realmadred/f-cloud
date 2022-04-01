1.安装heketi
#添加gluster yum源
#heketi-client：heketi客户端/命令行工具
[root@kube ~]#  yum -y install centos-release-gluster
[root@kube ~]#  yum -y install heketi heketi-client

2.配置heketi.json
[root@kube heketi]# cat heketi.json
```
{
"_port_comment": "Heketi Server Port Number",
"port": "8080",  #默认端口号

"_use_auth": "Enable JWT authorization. Please enable for deployment",
"use_auth": true,  #默认flase，不需要认证

"_jwt": "Private keys for access",
"jwt": {
"_admin": "Admin has access to all APIs",
"admin": {
"key": "admin"  #修改
},
"_user": "User only has access to /volumes endpoint",
"user": {
"key": "admin"  #修改
}
},

"_glusterfs_comment": "GlusterFS Configuration",
"glusterfs": {
"_executor_comment": [
"Execute plugin. Possible choices: mock, ssh",
"mock: This setting is used for testing and development.",
"      It will not send commands to any node.",
"ssh:  This setting will notify Heketi to ssh to the nodes.",
"      It will need the values in sshexec to be configured.",
"kubernetes: Communicate with GlusterFS containers over",
"            Kubernetes exec api."
],
#三种模式：
# mock：测试环境下创建的volume无法挂载；
# kubernetes：在GlusterFS由kubernetes创建时采用
"executor": "ssh",    #生产环境使用ssh或Kubernetes，这里采用ssh模式

    "_sshexec_comment": "SSH username and private key file information",
    "sshexec": {
      "keyfile": "/etc/heketi/heketi_key",   #密钥路径
      "user": "root",     #用户为root
      "port": "22",      
      "fstab": "/etc/fstab"
    },

    "_kubeexec_comment": "Kubernetes configuration",
    "kubeexec": {
      "host" :"https://kubernetes.host:8443",
      "cert" : "/path/to/crt.file",
      "insecure": false,
      "user": "kubernetes username",
      "password": "password for kubernetes user",
      "namespace": "OpenShift project or Kubernetes namespace",
      "fstab": "Optional: Specify fstab file on node.  Default is /etc/fstab"
    },

    "_db_comment": "Database file name",
    "db": "/var/lib/heketi/heketi.db",

    "_loglevel_comment": [
      "Set log level. Choices are:",
      "  none, critical, error, warning, info, debug",
      "Default is warning"
    ],
# 默认设置为debug，不设置时的默认值即是warning;
# 日志信息输出在/var/log/message
    "loglevel" : "warning"
}
}
```

3.设置heketi免密访问glusterFS
# 选择ssh执行器，heketi服务器需要免密登陆GlusterFS集群的各节点；
# -t：秘钥类型；
# -q：安静模式；
# -f：指定生成秘钥的目录与名字，注意与heketi.json的ssh执行器中"keyfile"值一致；
# -N：秘钥密码，””即为空
[root@kube ~]# ssh-keygen -t rsa -q -f /etc/heketi/heketi_key -N ""

# heketi服务由heketi用户启动，heketi用户需要有新生成key的读赋权，否则服务无法启动
[root@kube ~]# chown heketi:heketi /etc/heketi/heketi_key

# 分发公钥；
# -i：指定公钥
[root@kube ~]# ssh-copy-id -i /etc/heketi/heketi_key.pub root@192.168.200.182
[root@kube ~]# ssh-copy-id -i /etc/heketi/heketi_key.pub root@192.168.200.183
[root@kube ~]# ssh-copy-id -i /etc/heketi/heketi_key.pub root@192.168.200.184

4.启动heketi
[root@kube ~]#  systemctl enable heketi && systemctl start heketi && systemctl status heketi

#验证
[root@kube ~]# curl localhost:8080/hello
Hello from Heketi[root@kube ~]# 
