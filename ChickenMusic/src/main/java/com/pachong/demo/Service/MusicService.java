package com.pachong.demo.Service;

import com.pachong.demo.Entity.Params;
import com.pachong.demo.Tools.AesCBCUtils;
import com.pachong.demo.Tools.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.script.ScriptException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MusicService {

    @Autowired
    private AesCBCUtils aesCBCUtils;

    /**
     * 获取轮播图片信息
     */
    public String getSlider() throws IOException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/discover");
        Document doc = con.get();
        Elements elements = doc.getElementsByClass("m-cvrlst").first().getElementsByTag("li");
        return Utils.analyse(elements, "?param=800y800");
    }
    /**
     * 获取当前分类歌单
     */
    public String getDescByCat(String cat, Integer offset) throws IOException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/discover/playlist/?limit=35&cat=" + cat + "&offset=" + offset);
        Document doc = con.get();
        Elements elements = doc.getElementById("m-pl-container").getElementsByTag("li");
        return Utils.analyse(elements, "?param=150y150");
    }
    /**
     * 获取歌手Top100
     */
    public String getSinger() throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JSONException {
        Map data = new HashMap();
        Map head = new HashMap();
        Params params = aesCBCUtils.getParams("{\"offset\":\"0\",\"total\":\"true\",\"limit\":\"100\",\"csrf_token\":\"\"}");
        Connection con = Jsoup.connect("https://music.163.com/weapi/artist/top?csrf_token=");
        data.put("params", params.getParams());
        data.put("encSecKey", params.getEncSecKey());
        head.put("Accept","*/*");
        head.put("Content-Type", "application/x-www-form-urlencoded");
        head.put("Referer", "https://music.163.com/discover/artist");
        con.headers(head);
        con.data(data);
        Document doc = con.post();
        JSONObject object = new JSONObject(doc.getElementsByTag("body").first().text());
        return Utils.singer(object.getJSONArray("artists")).toString();
    }
    /**
     * 根据歌手id获取歌曲
     */
    public String getSongs(String id) throws IOException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/artist?id=" + id);
        Document doc = con.get();
        return Utils.getSong(new JSONArray(doc.getElementById("song-list-pre-data").text())).toString();
    }
    /**
     * 通过id获取歌词
     */
    public String getLyricById(String id) throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JSONException {
        Map data = new HashMap();
        Map head = new HashMap();
        String d = String.format("{\"id\":\"%s\",\"lv\":-1,\"tv\":-1,\"csrf_token\":\"\"}", id);
        Params params = aesCBCUtils.getParams(d);
        Connection con = Jsoup.connect("https://music.163.com/weapi/song/lyric?csrf_token=");
        data.put("params", params.getParams());
        data.put("encSecKey", params.getEncSecKey());
        head.put("Content-Type", "application/x-www-form-urlencoded");
        head.put("Accept","*/*");
        con.headers(head);
        con.data(data);
        Document doc = con.post();
        JSONObject object = new JSONObject(doc.getElementsByTag("body").text());
        try {
            JSONObject res = object.getJSONObject("lrc");
            return res.toString();
        } catch (Exception e){
            JSONObject res = new JSONObject();
            res.put("lyric", "[00:00.00]暂无歌词");
            return res.toString();
        }
    }
    /**
     * 根据id获取歌单歌曲
     */
    public String getList(String id) throws IOException, ScriptException, NoSuchMethodException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/playlist?id=" + id);
        Document doc = con.get();
        String key1 = doc.getElementById("song-list-pre-cache").getElementsByTag("a").get(0).attr("href");
        key1 = key1.substring(9, 12);
        String key2 = doc.getElementsByClass("j-img").first().attr("data-key");
        String key = "undefined" + key2 + key1;
        String encryptedjson = doc.getElementById("song-list-pre-data").text();
        JSONArray array = new JSONArray(Utils.compile(key, encryptedjson));
        return Utils.getSong1(array).toString();
    }
    /**
     * 获取排行榜
     */
    public String getTop() throws IOException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/discover/toplist");
        Document doc = con.get();
        JSONArray result = new JSONArray();
        Element element = doc.getElementsByClass("n-minelst").get(0);
        JSONObject object = new JSONObject();
        object.put("title", element.getElementsByTag("h2").get(0).text());
        object.put("detail", Utils.analyse1(element.getElementsByTag("ul").get(0).getElementsByTag("li")));
        result.put(object);
        object = new JSONObject();
        object.put("title", element.getElementsByTag("h2").get(1).text());
        object.put("detail", Utils.analyse1(element.getElementsByTag("ul").get(1).getElementsByTag("li")));
        result.put(object);
        return result.toString();
    }
    /**
     * 获取排行榜歌曲
     */
    public String getTopList(String id) throws IOException, JSONException {
        Connection con = Jsoup.connect("https://music.163.com/discover/toplist?id=" + id);
        Document doc = con.get();
        JSONArray jsonArray = new JSONArray(doc.getElementById("song-list-pre-data").text());
        return Utils.getSong(jsonArray).toString();
    }
    /**
     * 通过名字搜索音乐
     */
    public String searchByName(String musicName) throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JSONException {
        Map data = new HashMap();
        Map head = new HashMap();
        String d = String.format("{\"s\":\"%s\",\"offset\":0,\"limit\":30,\"type\":\"1\",\"total\":\"true\"}", musicName);
        Params params = aesCBCUtils.getParams(d);
        Connection con = Jsoup.connect("https://music.163.com/weapi/cloudsearch/get/web?csrf_token=");
        data.put("params", params.getParams());
        data.put("encSecKey", params.getEncSecKey());
        head.put("Content-Type", "application/x-www-form-urlencoded");
        head.put("Accept","*/*");
        con.headers(head);
        con.data(data);
        Document doc = con.post();
        try{
            JSONObject object = new JSONObject(doc.getElementsByTag("body").text());
            JSONArray array = object.getJSONObject("result").getJSONArray("songs");
            return Utils.getSong1(array).toString();
        }catch (Exception e){
            System.out.println(e);
            JSONObject er = new JSONObject();
            er.put("error", "没有找到结果");
            return er.toString();
        }
    }
}
