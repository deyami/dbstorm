package com.reform.dbstorm.client;

import org.testng.annotations.Test;

import com.reform.dbstorm.client.config.FileDbStormConfig;
import com.reform.dbstorm.zookeeper.ZKClient;

public class TestClient {

	@Test
	public void testGetChildren() {
		String endpoints = FileDbStormConfig.getConfig().getEndpoints();
		ZKClient zkClient = new ZKClient(endpoints);
		for (String p : zkClient.getChildren("/dbstorm")) {
			System.out.println(p);
		}

	}
}
