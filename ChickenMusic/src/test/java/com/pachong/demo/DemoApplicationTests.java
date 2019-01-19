package com.pachong.demo;

import com.pachong.demo.Controller.Controller;
import com.pachong.demo.Entity.Params;
import com.pachong.demo.Service.MusicService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private MusicService musicService;
    @Autowired
    private Controller controller;
    @Test
    public void contextLoads() throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JSONException, ScriptException, NoSuchMethodException {
//        musicService.getList("/playlist?id=2345955689");
//        musicService.compile("","");
    }
}
