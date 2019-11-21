
##
注意：${docker.image.prefix} 为你在 docker官方仓库的用户名，如果你不需要上传镜像，随便填。
通过maven 命令：
第一步：mvn clean
第二步： mvn package docker:build ,如下：
.....
镜像构建成功。查看镜像：docker images
显示：forezp/springboot-docker latest 60fdb5c61692 About a minute ago 195 MB
启动镜像：$ docker run -p 8080:8080 -t forezp/springboot-docker

打开浏览器访问 localhost:8080;浏览器显示：Hello Docker World。
说明docker 的springboot工程已部署。
停止镜像：
docker stop 60fdb5c61692
删除镜像：
docker rm 60fdb5c61692

## 参考
https://blog.csdn.net/forezp/article/details/71024219