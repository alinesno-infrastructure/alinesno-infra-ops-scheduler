package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.command.bean.ScriptDto;
import com.alinesno.infra.ops.scheduler.command.domain.CmdResult;
import com.alinesno.infra.ops.scheduler.command.runner.CmdExecutor;
import com.alinesno.infra.ops.scheduler.command.runner.Log;
import com.alinesno.infra.ops.scheduler.command.runner.LogListener;
import com.alinesno.infra.ops.scheduler.command.runner.ProcListener;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShellPlugin类是一个Shell插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Shell脚本任务。
 */
public class ShellPlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto dto) {
        // 创建CmdExecutor对象，并设置ProcListener和LogListener
        CmdExecutor executor = new CmdExecutor(new NullProcListener(dto),
                new AnsibelLogListener(dto),
                null,
                null,
                Lists.newArrayList("SHELL_RUNNER"),
                null,
                Lists.newArrayList(dto.getScriptContent()));
        // 运行脚本任务并获取执行结果
        CmdResult result = executor.run();

        System.out.println("result = " + result);
    }

    /**
     * NullProcListener类是一个空的ProcListener实现类。
     * 它用于处理CmdExecutor执行过程中的回调事件。
     */
    private static class NullProcListener implements ProcListener {

        private final Logger logProc = LoggerFactory.getLogger(NullProcListener.class);
        private ExecutorScriptDto dto ;

        public NullProcListener(ExecutorScriptDto dto) {
            this.dto = dto ;
        }

        @Override
        public void onStarted(CmdResult result) {
            System.out.println("---> onStarted ,  result = " + result.toJson());
            logProc.info("开始运行安装脚本:{}", result.toJson());
        }

        @Override
        public void onLogged(CmdResult result) {
            System.out.println("---> onLogged ,  result = " + result.toJson());
            logProc.info(result.toJson());
        }

        @Override
        public void onExecuted(CmdResult result) {
            System.out.println("---> onExecuted ,  result = " + result);
            logProc.info("运行脚本中:{}", result.toJson());
        }

        @Override
        public void onException(CmdResult result) {
            System.out.println("---> onException ,  result = " + result);
            logProc.error("运行脚本异常:{}", result);
        }
    }

    /**
     * AnsibelLogListener类是一个LogListener实现类。
     * 它用于处理CmdExecutor执行过程中的日志输出事件。
     */
    private static class AnsibelLogListener implements LogListener {

        private final Logger logProc = LoggerFactory.getLogger(AnsibelLogListener.class);
        private boolean isFinish = false;
        private String cmdLogContent = null;

        public AnsibelLogListener(ExecutorScriptDto dto) {
            logProc.debug("dto = {}" , dto);
        }

        @Override
        public void onLog(Log log) {
            cmdLogContent = log.getContent();

            if (cmdLogContent.contains("command not found")) {
                isFinish = false;
                logProc.info("命令行未找到:" + cmdLogContent);
            }

            logProc.info(cmdLogContent);
        }

        @Override
        public void onFinish() {
            System.out.println("读写完成.");
            logProc.info("脚本运行结束.");
            isFinish = true;
        }

        public boolean isFinish() {
            return isFinish;
        }

        @Override
        public String cmdLogContent() {
            return cmdLogContent;
        }
    }
}
