package com.testerhome.testapp.workspae;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Media {
    public Response add(){
        return given()
                .queryParam("type","image")
                .queryParam("access_token",WeChatWork.token)
                .multiPart(new File(getClass().getResource("/1.jpg").getFile()))
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/media/upload")
                .then()
                .extract().response();
    }
}
