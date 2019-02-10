package com.testerhome.testapp.workspae.api;

import com.testerhome.testapp.workspae.WeChatWork;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

public class WechatWorkBase {
    @BeforeClass
    public static void load(){
        if (WeChatWork.config==null) {
            WeChatWork.load("/default.yaml");
            WeChatWork.loadToken();
            RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        }
        System.out.println(WeChatWork.config);
    }
}
