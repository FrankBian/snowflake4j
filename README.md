# snowflake4j

## 需求分析
https://www.yuque.com/frankbian/distributed-design/emdmqy

## 开发计划

### 0.0.1

spring boot版本的rest接口, 提供功能:
+ /id/get
+ /id/exp?id=1246788543355
+ /id/produce?timestamp=xxx&dcId=xxx&workerId=xxx&seq=xxx

### 0.0.2

dubbo 版本的RPC服务实现,machineId只提供默认实现

### 0.0.3

docker 镜像,支持一键部署

### 0.1.0

batchmark 测试

### 0.1.1 

netty版本的rest接口实现

### 0.2.0

+ machineIdProvider可扩展
+ 配置数据梳理
+ 单机性能提升到1W QPS

