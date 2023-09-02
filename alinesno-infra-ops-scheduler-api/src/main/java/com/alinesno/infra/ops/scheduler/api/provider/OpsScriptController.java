package com.alinesno.infra.ops.scheduler.api.provider;

import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.dto.TaskInfoDto;
import com.alinesno.infra.ops.scheduler.entity.JobEntity;
import com.alinesno.infra.ops.scheduler.enums.JobTypeEnums;
import com.alinesno.infra.ops.scheduler.service.IExecutorScriptService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 构建任务对外服务
 */
@Scope
@RestController
@RequestMapping("/v1/api/ops/scheduler/")
public class OpsScriptController {

    private static final Logger log = LoggerFactory.getLogger(OpsScriptController.class);

    @Autowired
    private IExecutorScriptService executorScriptService ;

    /**
     * 运行数据抽取服务
     *
     * @param dto 执行器脚本的DTO对象，包含执行器脚本的相关信息
     * @return 返回执行结果的AjaxResult对象
     * @throws SQLException 如果执行过程中发生SQL异常
     * @throws IOException  如果执行过程中发生IO异常
     */
    @PostMapping("defaultTask")
    public AjaxResult runPipeline(@RequestBody TaskInfoDto dto) throws SQLException, IOException {

        log.debug("script dto = {}", new Gson().toJson(dto));

        JobEntity job =  executorScriptService.analyzeTaskInfo(dto) ;

        return AjaxResult.success("任务保存成功." , job.getId());
    }
}
