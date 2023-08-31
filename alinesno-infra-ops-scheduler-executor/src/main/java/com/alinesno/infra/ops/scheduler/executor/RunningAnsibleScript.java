package com.alinesno.infra.ops.scheduler.executor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.alinesno.infra.ops.scheduler.command.bean.AnsibleScriptDto;
import com.alinesno.infra.ops.scheduler.command.constants.ExecuteConst;
import com.alinesno.infra.ops.scheduler.command.domain.CmdResult;
import com.alinesno.infra.ops.scheduler.command.runner.CmdExecutor;
import com.alinesno.infra.ops.scheduler.command.runner.Log;
import com.alinesno.infra.ops.scheduler.command.runner.LogListener;
import com.alinesno.infra.ops.scheduler.command.runner.ProcListener;
import com.alinesno.infra.ops.scheduler.exception.ExecutorServiceRuntimeException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

/**
 * 运行ansible操作的引擎
 * @author luoxiaodong
 * @version 1.0.0
 */
public class RunningAnsibleScript {

	private static final Logger log = LoggerFactory.getLogger(RunningAnsibleScript.class);
	private static final String WHITE_TEXT = " " ; 
	
	/**
	 * 运行脚本任务
	 */
	public void run(AnsibleScriptDto dto) {
		
		log.debug("AnsibleScriptDto = {}" , dto);
		
		Assert.hasLength(dto.getJobWorkspace() , "空间路径不能为空");
		Assert.hasLength(dto.getPlaybook() , "运行剧本为空");
		Assert.hasLength(dto.getInventory() , "运行主机清单为空");
		Assert.hasLength(dto.getInstallation() , "运行路径不能为空");
		
		StringBuffer script = new StringBuffer() ;
		
		script.append(dto.getInstallation()) ; 
		script.append(WHITE_TEXT) ;
		script.append("-i ").append(dto.getInventory());
		script.append(WHITE_TEXT) ;
		script.append(dto.getPlaybook()) ; 
		
		if(StringUtils.isNotBlank(dto.getExtras())) {
			script.append(WHITE_TEXT) ;
			script.append(dto.getExtras()) ; 
		}
		
		String scriptStr = script.toString() ; 
		
		log.debug("运行ansible脚本:{}", scriptStr);
		
		CmdExecutor executor1 = new CmdExecutor(new NullProcListener(dto), new AnsibelLogListener(dto), null, null, Lists.newArrayList("ANSIBLE_SHELL_RUNNER"), null, Lists.newArrayList(scriptStr));
		CmdResult result1 = executor1.run();
	
		if(result1.getExitValue() != 0) {
			String logPath = dto.getJobWorkspace() + File.separator + ExecuteConst.running_logger_fileName ;
			String logStr = "" ;
			try {
				logStr = FileUtils.readFileToString(new File(logPath), Charset.defaultCharset());
			} catch (IOException e) {
				log.error("找不到文件:{}" , logPath);
			}
			throw new ExecutorServiceRuntimeException("脚本【" + dto.getPlaybook() + "】运行异常，无法正常运作.\r\n" + logStr ) ;
		}
		
		log.debug("result = {}", result1);
		
	}
	
	private static class NullProcListener implements ProcListener {

		private final Logger logProc = LoggerFactory.getLogger(NullProcListener.class);
		private AnsibleScriptDto AnsibleScriptDto ; 
		
		public NullProcListener(AnsibleScriptDto dto) {
			
			this.AnsibleScriptDto = dto ; 
			
			logProc.debug("dto = {}" , this.AnsibleScriptDto);
			
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
			
			AnsibleScriptDto.setRunLogger(result.toJson());
		}

		@Override
		public void onExecuted(CmdResult result) {
			System.out.println("---> onExecuted ,  result = " + result.toJson());
			logProc.info("运行脚本中:{}", result.toJson());
		}

		@Override
		public void onException(CmdResult result) {
			System.out.println("---> onException ,  result = " + result.toJson());
			logProc.error("运行脚本异常:{}", result.toJson());

			throw new ExecutorServiceRuntimeException("运行脚本异常");
		}
	}

	private static class AnsibelLogListener implements LogListener {

		private final Logger logProc = LoggerFactory.getLogger(AnsibelLogListener.class);
		private boolean isFinish = false;
		private String cmdLogContent = null;
		
		private String logPath ; 

		private AnsibleScriptDto AnsibleScriptDto ; 
		
		public AnsibelLogListener(AnsibleScriptDto dto) {
			this.AnsibleScriptDto = dto ; 

			logPath = dto.getJobWorkspace() + File.separator + ExecuteConst.running_logger_fileName ;
			
			logProc.debug("dto = {}" , this.AnsibleScriptDto);
		}

		@Override
		public void onLog(Log loger) {
			cmdLogContent = loger.getContent();

			if (cmdLogContent.contains("command not found")) {
				isFinish = false;
				throw new ExecutorServiceRuntimeException("命令行未找到:" + cmdLogContent);
			}

			logProc.info(cmdLogContent);
		
			try {
				FileUtils.writeStringToFile(new File(logPath), cmdLogContent + "\r\n" , Charset.defaultCharset() , true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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

	};
	
}
