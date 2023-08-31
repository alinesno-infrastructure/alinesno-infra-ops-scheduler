package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.service.IExecutorScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 运行脚本
 */
@Service
public class ExecutorScriptServiceImpl implements IExecutorScriptService {

    private static final Logger log = LoggerFactory.getLogger(ExecutorScriptServiceImpl.class) ;

    @Override
    public void run(ExecutorScriptDto executorScriptDto) {

        log.debug("运行脚本服务:{}" , executorScriptDto.toString());



    }
}

