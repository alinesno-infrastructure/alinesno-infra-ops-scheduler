# alinesno-infra-ops-scheduler
提供自动化任务调度和管理功能，包括业务自动运维服务，运维任务编排

## 处理脚本

集成一个简单的CICD流程配置，主要流程如下:

```shell
git clone https://github.com/alinesno-infrastructure/alinesno-infra-ops-scheduler.git
mvn clean package install 
sftp copy to server 
docker run -p xxxx
send result to webhook
check headlth
send dingtalk notices
```

集成下面的示例任务配置:
 
```json
{
  "taskType":"workflow",
  "name":"脚本依赖信息",
  "describe":"测试任务流程信息",
  "settings":[
    {
      "key":"cron",
      "value":"*/30 * * * * ?"
    }
  ],
  "env":[
    {
      "key":"JAVA_HOME",
      "value":"/ops/java"
    },
    {
      "key":"GIT_TOKEN",
      "value":"xxxx"
    }
  ],
  "workflow":[
    {
      "name":"初始化环境配置",
      "id":"node_01",
      "type":"start",
      "plugin":{
        "name":"shell",
        "contextScript":"#!/bin/bash\n\nfor ((i\u003d1; i\u003c\u003d1000; i++))\ndo\n    echo \"循环次数: $i\"\n    # 在这里添加你要执行的命令或操作\ndone\n"
      },
      "parentStep":"start"
    },
    {
      "name":"查询运行指标",
      "id":"node_02",
      "type":"node",
      "plugin":{
        "name":"clickhouse",
        "pluginAttributes":[
          {
            "key":"query_sql",
            "value":"select count(1) from kfinfo"
          },
          {
            "key":"driver_class",
            "value":"ru.yandex.clickhouse.ClickHouseDriver"
          },
          {
            "key":"url",
            "value":"jdbc:clickhouse://localhost:8123/default"
          },
          {
            "key":"username",
            "value":"default"
          },
          {
            "key":"password",
            "value":""
          }
        ]
      },
      "parentStep":"node_01"
    },
    {
      "name":"发送消息通知",
      "id":"node_03",
      "type":"node",
      "plugin":{
        "name":"shell",
        "contextScript":"echo \u0027send alert\u0027"
      },
      "parentStep":"node_02"
    }
  ]
}
```

## 常见问题

jdk17添加以下到vm
```sh
--add-opens java.base/java.lang=ALL-UNNAMED
```

## 鸣谢

- 设计思路参考:[蓝鲸运维]()，[阿里自动任务]()
- 这里自动化工具使用[ansible](https://github.com/ansible/ansible)框架
- CMD处理部分使用[flow.ci](flow.ci)处理源码
- 集成[kjyw](https://gitee.com/aqztcom/kjyw)脚本进行演示示例和内置脚本功能