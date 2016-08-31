package com.aiggo.common.util;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;

/**
 * 资源关闭辅助类
 * @author woodle
 *
 */
public class CloseIOUtil {

    private static Logger logger = Logger.getLogger(CloseIOUtil.class);
	
	public static void closeResouce(Closeable closeable) throws IOException {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
			closeable = null;
		} catch (IOException e) {
            logger.error( "IOException thrown while closing Closeable."+ e);
		}
	}
	
	public static void closeResouces(Closeable... clos) throws IOException {
		for (Closeable closeable : clos) {
			closeResouce(closeable);
		}
	}
}
