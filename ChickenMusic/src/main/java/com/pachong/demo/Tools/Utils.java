package com.pachong.demo.Tools;

import com.pachong.demo.Service.MusicService;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utils {

    public static String analyse(Elements elements, String size) throws JSONException {
        JSONArray result = new JSONArray();
        for (Element e: elements){
            String href = e.getElementsByTag("a").first().attr("href");
            if (href.substring(1, href.indexOf("?")).equals("dj"))
                continue;
            JSONObject object = new JSONObject();
            String picture = e.getElementsByTag("img").first().attr("src");
            picture = picture.substring(0, picture.indexOf("?")) + size;
            String title = e.getElementsByTag("a").first().attr("title");
            String id = href.substring(href.indexOf("=") + 1);
            String name = e.getElementsByTag("a").last().attr("title");
            object.put("picture", picture);
            object.put("title", title);
            object.put("id", id);
            object.put("name", name);
            result.put(object);
        }
        return result.toString();
    }

    public static JSONArray singer(JSONArray array) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i=0; i<array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            JSONObject object1 = new JSONObject();
            object1.put("picture", object.get("picUrl"));
            String name = object.get("name").toString();
            object1.put("name", name);
            object1.put("id", object.get("id"));
            object1.put("key", getHeader(name));
            res.put(object1);
        }
        return res;
    }

    public static String getHeader(String str){
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase().substring(0,1);
    }

    public static JSONArray getSong(JSONArray array) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i=0; i<array.length(); i++){
            JSONObject object = array.getJSONObject(i);
            JSONObject object1 = new JSONObject();
            object1.put("id", object.get("id"));
            JSONArray artists = object.getJSONArray("artists");
            JSONArray singers = new JSONArray();
            for (int j=0; j<artists.length(); j++){
                singers.put((new JSONObject()).put("name", artists.getJSONObject(j).get("name")));
            }
            object1.put("singer", singers);
            object1.put("duration", Integer.parseInt(object.get("duration").toString())/1000);
            object1.put("picture", object.getJSONObject("album").get("picUrl"));
            object1.put("album", object.getJSONObject("album").get("name"));
            object1.put("name", object.get("name"));
            res.put(object1);
        }
        return res;
    }

    public static JSONArray getSong1(JSONArray array) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i=0; i<array.length(); i++){
            JSONObject object = array.getJSONObject(i);
            JSONObject object1 = new JSONObject();
            object1.put("id", object.get("id"));
            JSONArray artists = object.getJSONArray("ar");
            JSONArray singers = new JSONArray();
            for (int j=0; j<artists.length(); j++){
                singers.put((new JSONObject()).put("name", artists.getJSONObject(j).get("name")));
            }
            object1.put("singer", singers);
            object1.put("duration", Integer.parseInt(object.get("dt").toString())/1000);
            object1.put("picture", object.getJSONObject("al").get("picUrl"));
            object1.put("album", object.getJSONObject("al").get("name"));
            object1.put("name", object.get("name"));
            res.put(object1);
        }
        return res;
    }

    public static JSONArray analyse1(Elements es) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (int i=0; i<es.size(); i++){
            JSONObject ob = new JSONObject();
            ob.put("src", es.get(i).getElementsByTag("img").get(0).attr("src").
                    replaceAll("40y40", "150y150"));
            ob.put("id", es.get(i).getElementsByTag("a").get(1).attr("href").split("=")[1]);
            ob.put("name", es.get(i).getElementsByTag("a").get(1).text());
            ob.put("tip", es.get(i).getElementsByTag("p").get(1).text());
            jsonArray.put(ob);
        }
        return jsonArray;
    }

    public static String compile(String key, String json) throws ScriptException, NoSuchMethodException {
        ScriptEngine jsEngine = (new ScriptEngineManager()).getEngineByName("nashorn");
        jsEngine.eval(new BufferedReader(new InputStreamReader(MusicService.class.getResourceAsStream("/static/decode.js"))));
        Invocable invocableEngine = (Invocable) jsEngine;
        return  (String) invocableEngine.invokeFunction("getResult", json, key);
    }
}
