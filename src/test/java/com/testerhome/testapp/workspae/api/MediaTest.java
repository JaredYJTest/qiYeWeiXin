package com.testerhome.testapp.workspae.api;

import com.testerhome.testapp.workspae.Media;
import com.testerhome.testapp.workspae.WeChatWork;
import org.junit.Test;

import static org.junit.Assert.*;

public class MediaTest extends WechatWorkBase {

    @Test
    public void add() {
        Media m = new Media();
        m.add().then().statusCode(200);
    }
}