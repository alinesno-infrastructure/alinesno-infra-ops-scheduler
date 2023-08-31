# alinesno-infra-ops-scheduler
提供自动化任务调度和管理功能，包括业务自动运维服务，运维任务编排

## 处理脚本

```yaml
job:
  name: xxxx
  settings:
    cron: xxxxx
  env:
    JAVA_HOME: /ops/java
    GIT_TOKEN: xxx
  workflow:
    - step:
        name: '初始化项目配置'
        id: node-3
        type: node
        plugin:
          name: shell 
          contextScript:
        parentStep: 'start' 
    - step:
        name: '数据指标查询'
        id: node-3
        type: clickhouse 
        plugin:
          name: clickhouse
          url: 
          username:
          password:
        parentStep: 'node-3'
    - step:
        name: '条件判断'
        id: node-3
        type: condition 
        plugin:
          name: condition 
          contextScript:
        parentStep: 'node-3'
    - step:
        name: '运行Ansible脚本'
        id: node-1
        type: node
        plugin:
          name: ansible 
          scriptContext:
        parentStep: 'node-3'
    - step:
        name: '运行Groovy脚本'
        id: node-2
        type: node
        plugin:
          name: groovy 
          scriptContext:
        parentStep: 'node-3'
    - step:
        name: '运行通知'
        id: node-2
        type: alarm 
        plugin:
          name: dingtalk
          webhook: 
          secret:
        parentStep: 'node-3'
```

## 常见问题

jdk17添加以下到vm
```sh
--add-opens java.base/java.lang=ALL-UNNAMED
```

## 鸣谢

- 这里自动化工具使用[ansible](https://github.com/ansible/ansible)框架
- CMD处理部分使用[flow.ci](flow.ci)处理源码
- 集成[kjyw](https://gitee.com/aqztcom/kjyw)脚本进行演示示例和内置脚本功能