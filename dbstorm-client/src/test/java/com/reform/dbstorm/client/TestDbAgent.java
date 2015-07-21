package com.reform.dbstorm.client;

import com.reform.dbstorm.client.ds.StormDataSourcePool;

public class TestDbAgent {

    public static void main(String[] args) {
        DbAgent ag = DbAgent.getInstance();
        StormDataSourcePool source = ag.getDsPool("dcs_cube_pagerefer");
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
