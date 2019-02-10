package com.testerhome.testapp.workspae;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class Department {

    public DocumentContext departmentData;
    public void load(){
        departmentData =WeChatWork.readJson("/department.json");


    }


    public void setCentent(String key, Object value){
        departmentData.set(JsonPath.compile(key),value);
    }
    public String getContent(){
        return departmentData.jsonString();

    }
    public Response add(String body){
        return given().contentType(ContentType.JSON)
                .queryParam("access_token",WeChatWork.token)
                .body(departmentData.jsonString())
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .statusCode(200)
                .extract().response();

    }

   public Response query(String id){
       return given().contentType(ContentType.JSON)
               .queryParam("access_token",WeChatWork.token)
               .queryParam("id",id)
               .when()
               .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
               .then()
               .statusCode(200)
               .extract().response();
   }
   public Response delete(String id){
       return given().contentType(ContentType.JSON)
               .queryParam("access_token",WeChatWork.token)
               .queryParam("id",id)
               .when()
               .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
               .then()
               .statusCode(200)
               .extract().response();
   }
}
