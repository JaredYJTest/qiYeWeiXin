package com.testerhome.testapp.workspae.api;

import com.testerhome.testapp.workspae.Department;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.tika.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;


@RunWith(Parameterized.class)
public class DepartmentTest extends WechatWorkBase {
    public static Department department;


    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"4","接口测试第二期"},{"5","接口测试第三期"},{"6","接口测试第四期"}
        });
    }
    @Parameterized.Parameter
    public String number;
    @Parameterized.Parameter(1)
    public String name;
    @BeforeClass
    public static void beforeClass(){
        department =new Department();


    }

    @DisplayName("add department")
    @Description("测试第三条数据")
    @Test
    public void add() {
        department.delete(number).then().statusCode(200);

        FileInputStream fileInputFile = null;
        try {
            fileInputFile = new FileInputStream(new File(getClass().getResource("/2.jpg").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String targetFileStr = null;
        try {
            targetFileStr = IOUtils.toString(fileInputFile,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Allure.addAttachment("demo","test");
        Allure.addAttachment("logo","jpg",
                targetFileStr);


        department.load();
        department.setCentent("$.name", name);
        department.setCentent("$.id", number);
        department.add(department.getContent())
            .then()
                .statusCode(200)
                .body("errcode", equalTo(0));
        department.query(number)
                .then()
                .statusCode(200)
                .body("department.find{d -> d.id == " + number + "}.name",equalTo(name));
    }

}