package com.testerhome.testapp.workspae;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.swing.text.Document;
import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class Menber {

    public DocumentContext member;
    public void loadMember(){
        member =WeChatWork.readJson("/member.json");


    }

    public void setCentent(String key, Object value){
        member.set(JsonPath.compile(key),value);
    }
    public String getContent(){
        return member.jsonString();

    }

    public Response add(String body){
        return given()
                .contentType(ContentType.JSON)
                .queryParam("access_token",WeChatWork.token)
                .body(body)
            .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
            .then()
                .statusCode(200)
                .extract().response();
    }
    public Response delete(String id){
        return given()
                .queryParam("userid",id)
                .queryParam("access_token",WeChatWork.token)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then()
                .extract().response();

    }
    public Response query(String id){
        return given()
                .queryParam("userid",id)
                .queryParam("access_token",WeChatWork.token)
            .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
            .then()
                .extract().response();


    }

}
