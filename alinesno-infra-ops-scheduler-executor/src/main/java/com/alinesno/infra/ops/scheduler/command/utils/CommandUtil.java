package com.alinesno.infra.ops.scheduler.command.utils;

/**
 * 配置
 * 
 * @author LuoAnDong
 * @since 2022年8月20日 上午6:23:43
 */
public class CommandUtil {

	public static class Unix {

		public final static String LINE_SEPARATOR = "\n";

		public final static String PATH_SEPARATOR = "/";

		public final static String CMD_EXECUTOR = "/bin/bash";
	}

	public static class Mac {

		public final static String LINE_SEPARATOR = "\n";

		public final static String PATH_SEPARATOR = "/";

//		public final static String CMD_EXECUTOR = "/usr/local/bin";
		
		public final static String CMD_EXECUTOR = "/Users/luodong/Desktop";
		
	}

	public static class Windows {

		public final static String LINE_SEPARATOR = "\r\n";

		public final static String PATH_SEPARATOR = "\\";

		public final static String CMD_EXECUTOR = "C:\\cygwin\\bin\\bash.exe";
	}
}
