package com.testerhome.testapp.workspae;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class WeChatWorkTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() {
        String path = "./1.yaml";
        assertEquals(WeChatWork.config,null);
        WeChatWork.config = new Config();
        WeChatWork.config.setCopId("demo");
        WeChatWork.write(path);
        WeChatWork.config.setCopId("demo2");
        WeChatWork.load(new File(path));
        assertEquals(WeChatWork.config.getCopId(),"demo");
    }
}