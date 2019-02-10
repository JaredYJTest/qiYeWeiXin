package com.testerhome.testapp.workspae.api;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.testerhome.testapp.workspae.Menber;
import com.testerhome.testapp.workspae.WeChatWork;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.lang.reflect.Parameter;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class MemberTest  extends WechatWorkBase{
    static Menber m = new Menber();
    public static String id="zhangsan1";
    private String name="xxx_x";

    @BeforeClass
    public static void beforeClassMember(){
        m.loadMember();
        m.setCentent("$.email",id+"@testerhome.com");
        m.setCentent("$.mobile",String.valueOf(System.currentTimeMillis()));
        m.delete(id);
        //remove
    }


    @Test
    public void addMamber(){
        m.setCentent("$.userid",id);
        m.setCentent("$.external_profile.external_attr[2].miniprogram.title","ttt");

        m.setCentent("$.name", name);
        m.add(m.getContent())
                .then()
                .statusCode(200);
        m.query(id).then().body("name",equalTo(name));


    }
    @Test
    public void addException(){
        String udid=String.valueOf(System.currentTimeMillis());

        m.setCentent("$.userid",udid);
        m.setCentent("$.name", udid);
        m.setCentent("$.email",udid+"@testerhome.com");
        m.setCentent("$.mobile",udid);
        m.setCentent("$.external_profile.external_attr[2].miniprogram.title","ttt");

        m.add(m.getContent())
                .then()
                .statusCode(200);

        m.query(udid).then().body("name",equalTo(udid));

        m.setCentent("$.userid",udid);
        m.setCentent("$.name", udid);
        m.setCentent("$.email",udid+"@testerhome.com");
        m.setCentent("$.mobile",udid);
        m.setCentent("$.external_profile.external_attr[2].miniprogram.title","ttt");

        m.add(m.getContent())
                .then()
                .statusCode(200).body("errcode",not(equalTo(0)))
                .body("errcode",equalTo(60104));
    }
    @AfterClass
    public static void afterMemberClass(){

    }
}
