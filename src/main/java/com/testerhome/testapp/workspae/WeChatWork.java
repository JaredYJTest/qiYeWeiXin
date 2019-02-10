package com.testerhome.testapp.workspae;

import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class WeChatWork {
    public static Config config=null;
    public static String token=null;
    public static void load(File file){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config= mapper.readValue(file,Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void load(String path){
        load(new File(WeChatWork.class.getResource(path).getFile()));
    }
    public static void write(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(path),config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadToken(){
        if (token==null) {
            token = given()
                    .queryParam("corpid", WeChatWork.config.getCopId())
                    .queryParam("corpsecret", WeChatWork.config.getContactToken())
                    .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then()
                    .statusCode(200)
                    .body("errcode", equalTo(0)).extract().path("access_token");
        }
        System.out.println(token);
    }
    public static DocumentContext readJson(String path){
        DocumentContext json=JsonPath.parse(WeChatWork.class.getResourceAsStream(path));
        return json;

    }
}
