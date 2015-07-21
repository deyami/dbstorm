package com.reform.dbstorm.zookeeper;

import java.io.UnsupportedEncodingException;

import com.reform.dbstorm.zookeeper.exception.ZKException;
import org.testng.annotations.Test;

public class TestZKClient {

	String		path	= "/dbstorm/reform_test";
	ZKClient	zk		= new ZKClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
	String		conf	= "<instance name=\"online_user_action\" type=\"singler\" timestamp=\"2009-07-31 10:33:36\"><server type=\"mysql\" database=\"app\" host=\"db3\" port=\"3306\" user=\"mophappy\" password=\"m0p50happy\" wrflag=\"wr\" charset=\"utf8\" priority=\"\" /><server type=\"mysql\" database=\"app\" host=\"db3\" port=\"3306\" user=\"mophappy\" password=\"m0p50happy\" wrflag=\"r\" charset=\"utf8\" priority=\"\" /></instance>";

	public void testCreate() {
		zk.createPersistent(path, conf, new DataSerializer<String>() {

			public byte[] serialize(final String obj) {
				try {
					return obj.getBytes("utf-8");
				} catch (UnsupportedEncodingException e) {
					throw new ZKException(e);
				}
			}

		}, true);
	}

	@Test
	public void testSet() {
		zk.writeData(path, conf, new DataSerializer<String>() {

			public byte[] serialize(final String obj) {
				try {
					return obj.getBytes("utf-8");
				} catch (UnsupportedEncodingException e) {
					throw new ZKException(e);
				}
			}

		}, -1);
	}

}
