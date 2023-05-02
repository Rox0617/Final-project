package com.demo.tianqi.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.demo.tianqi.feign.client.TestServiceClient;
import com.demo.tianqi.util.Constants;
import com.demo.tianqi.util.RedisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 天气Controller
 * @desc WeatherController
 **/
@RequestMapping("/tianqi")
@Controller
public class TQController {

    @Autowired
    private TestServiceClient testServiceClient;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取天气信息
     * http://localhost:30007/tianqi/getTQ?city=上海市
     * @return
     */
    @RequestMapping("/getTQ")
    @ResponseBody
    public String getTianQiInfo(String city){
        Map<String,Object> formMap = new HashMap<>();
        String info = null;
        //第一次取先去redis中看一下是否有，如果有直接取，
        // 没有的话，然后调用接口，获取到的数据存到redis数据库中
        if(redisUtil.get("tianqi") != null){
            info = redisUtil.get("tianqi");
        }else{
            //接口地址https://www.mxnzp.com/api/weather/current/{城市名}
            String url="https://www.mxnzp.com/api/weather/current/"+city+"?app_id="+ Constants.APP_ID+"&app_secret="+Constants.APP_SECRET;
            //链式构建请求
            String result2 = HttpRequest.post(url)
                    .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                    .form(formMap)//表单内容
                    .execute().body();
            /**
             * "code":1,"msg":"数据返回成功！","data":{"address":"上海市","cityCode":"310000","temp":"21℃","weather":"阴","windDirection":"北","windPower":"≤3级","humidity":"83%","reportTime":"2023-04-19 17:03:42"}}
             */
            JSONObject jsonObject = JSONObject.fromObject(result2);

            //获取数据
            JSONObject data = jsonObject.getJSONObject("data");
            //获取地址
            String address = data.get("address").toString();
            //获取温度
            String temp = data.get("temp").toString();
            //获取天气
            String weather = data.get("weather").toString();
            //获取风向
            String windDirection = data.get("windDirection").toString();
            //获取风力
            String windPower = data.get("windPower").toString();
            //获取湿度值
            String reportTime = data.get("reportTime").toString();
            //获取日期
            String day = DateUtil.today();
            info = "日期:"+day+" 城市："+address+" 天气："+weather+" 温度："+temp+" 风向："+windDirection+" 风力："+windPower+" 发布时间："+reportTime;
            System.out.println("返回天气信息"+"日期:"+info);
            redisUtil.set("tianqi",info);
        }
        return info;
    }







    /**
     * 获取天气信息
     * http://localhost:30002/tianqi/getTQ?city=上海市
     * @return
     */
    @RequestMapping("/getwebTQ")
    @ResponseBody
    public JSONObject getWebTianQiInfo(String city){
        JSONObject restObject = new JSONObject();
        Map<String,Object> formMap = new HashMap<>();
        String info = null;
        //接口地址https://www.mxnzp.com/api/weather/current/{城市名}
        String url="https://www.mxnzp.com/api/weather/forecast/"+city+"?app_id="+ Constants.APP_ID+"&app_secret="+Constants.APP_SECRET;
        //链式构建请求
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(formMap)//表单内容
                .execute().body();
        /**
         * "code": 1,
         *     "msg": "数据返回成功",
         *     "data": {
         *         "address": "广东省 深圳市",
         *         "cityCode": "440300",
         *         "temp": "18℃",
         *         "weather": "小雨",
         *         "windDirection": "东北",
         *         "windPower": "≤3级"，
         *         "reportTime": "2018-11-27 22:40:53"
         */
        JSONObject jsonObject = JSONObject.fromObject(result2);
        List<Map<String,Object>> list = new ArrayList<>();
        if(Integer.valueOf(jsonObject.get("code").toString())==0){
            restObject.put("code",0);
            restObject.put("msg",jsonObject.get("msg").toString());

        }else{
            restObject.put("code",1);
            //获取数据
            JSONObject data = jsonObject.getJSONObject("data");
            //获取地址
            String address = data.get("address").toString();

            String reportTime = data.get("reportTime").toString();
            //获取温度
//            String temp = data.get("temp").toString();
//            //获取天气
//            String weather = data.get("weather").toString();
//            //获取风向
//            String windDirection = data.get("windDirection").toString();
//            //获取风力
//            String windPower = data.get("windPower").toString();
//            //获取湿度值
//            String humidity = data.get("reportTime").toString();
//           //获取日期
//            String day = DateUtil.today();

//            restObject.put("day",day);
//            restObject.put("city",address);
//            restObject.put("temp",temp);
//            restObject.put("weather",weather);
//            restObject.put("windDirection",windDirection);
//            restObject.put("windPower",windPower);
//            restObject.put("reportTime",reportTime);

            JSONArray array= data.getJSONArray("forecasts");
            for(int i = 0;i<array.size();i++){
                Map<String,Object> map = new HashMap<>();
                map.put("day",array.getJSONObject(i).get("date"));
                map.put("weather",array.getJSONObject(i).get("dayWeather"));
                map.put("temp",array.getJSONObject(i).get("dayTemp"));
                map.put("windDirection",array.getJSONObject(i).get("dayWindDirection"));
                map.put("windPower",array.getJSONObject(i).get("dayWindPower"));
                map.put("reportTime",reportTime);
                map.put("city",address);
                list.add(map);
            }
            restObject.put("tlist",list);
        }
        return restObject;
    }












    /**
     * 利用fegin调用test服务方法
     * @return
     */
    @RequestMapping("/ROXtest")
    @ResponseBody
    public String ROXTest(){
        String GaiyueFailure = testServiceClient.testRox();
        System.out.println("RoxLi========="+GaiyueFailure);
        return GaiyueFailure;
    }


}
