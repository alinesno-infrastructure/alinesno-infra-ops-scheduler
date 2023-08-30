package com.alinesno.infra.ops.scheduler.api.provider;

import com.alinesno.infra.common.facade.response.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/v1/api/ops/pipeline/")
public class OpsScriptController {

    private static final Logger log = LoggerFactory.getLogger(OpsScriptController.class) ;

    /**
     * 运行数据抽取服务
     * @return
     */
    @PostMapping("runPipeline")
    public AjaxResult runPipeline(@RequestBody String scriptContext , String scriptType) throws SQLException, IOException {


        return AjaxResult.success("任务保存成功.") ;
    }

}