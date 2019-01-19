package com.pachong.demo.Controller;

import com.pachong.demo.Service.MusicService;
import org.hibernate.validator.constraints.pl.REGON;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.script.ScriptException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/music")
@CrossOrigin(allowCredentials = "true")
public class Controller {

    @Autowired
    private MusicService musicService;

    /**
     * 获取轮播图信息
     */
    @GetMapping(value = "/getSlider")
    public String getSlider() throws IOException, JSONException {
        return musicService.getSlider();
    }

    /**
     * 按分类获取歌单
     */
    @GetMapping(value = "/getDisc")
    public String cat(@RequestParam("cat") String cat, @RequestParam("offset") Integer offset) throws IOException, JSONException {
        return musicService.getDescByCat(cat, offset);
    }
    /**
     * 获取歌手Top100
     */
    @GetMapping(value = "/getSinger")
    public String getSinger() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, JSONException {
        return musicService.getSinger();
    }
    /**
     * 根据歌手id获取歌曲
     */
    @GetMapping(value = "/getSongs")
    public String getSongs(@RequestParam("id") String id) throws IOException, JSONException {
        return musicService.getSongs(id);
    }
    /**
     * 获取歌词
     */
    @GetMapping(value = "/lyric")
    public String lyric(@RequestParam("id") String id) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IOException, JSONException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return musicService.getLyricById(id);
    }
    /**
     * 获取歌单歌曲
     */
    @GetMapping(value = "/playList")
    public String getList(@RequestParam("id") String id) throws IOException, JSONException, ScriptException, NoSuchMethodException {
        return musicService.getList(id);
    }
    /**
     * 获取排行榜
     */
    @GetMapping(value = "/topList")
    public String getTopList() throws IOException, JSONException {
        return musicService.getTop();
    }
    /**
     * 获取排行榜歌曲
     */
    @GetMapping(value = "/topSong")
    public String topSong(@RequestParam("id") String id) throws IOException, JSONException {
        return musicService.getTopList(id);
    }
    /**
     * 搜索
     */
    @GetMapping(value = "/search")
    public String search(@RequestParam("msg") String msg) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IOException, JSONException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return musicService.searchByName(msg);
    }
}
