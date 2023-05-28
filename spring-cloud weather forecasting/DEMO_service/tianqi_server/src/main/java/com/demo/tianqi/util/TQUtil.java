package com.demo.tianqi.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 天气接口工具 Weather interface tool
 **/
public class TQUtil {

    public static String getTQ(String city){
        Map<String,Object> formMap = new HashMap<>();
        //接口地址https://www.mxnzp.com/api/weather/current/{城市名}
        String url="https://www.mxnzp.com/api/weather/current/"+city+"?app_id="+ Constants.APP_ID+"&app_secret="+Constants.APP_SECRET;
        //链式构建请求 Chain build request
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可 Header information, multiple header information can be called multiple times this method
                .form(formMap)//表单内容 form properties
                .execute().body();
        /**
         * "code":1,"msg":"数据返回成功！","data":{"address":"上海市","cityCode":"310000","temp":"21℃","weather":"阴","windDirection":"北","windPower":"≤3级","humidity":"83%","reportTime":"2023-04-19 17:03:42"}}
         */
        JSONObject jsonObject = JSONObject.fromObject(result2);
        //获取数据 get data
        JSONObject data = jsonObject.getJSONObject("data");
        //获取地址 get address
        String address = data.get("address").toString();
        //获取温度 get temperature
        String temp = data.get("temp").toString();
        //获取天气 get  weather
        String weather = data.get("weather").toString();
        //获取风向 get wind direction
        String windDirection = data.get("windDirection").toString();
        //获取风力 get wind power
        String windPower = data.get("windPower").toString();
        //获取发布时间 get report time
        String reportTime = data.get("reportTime").toString();
        //获取日期 get date
        String day = DateUtil.today();
        String msg= "日期:"+day+" 城市："+address+" 天气："+weather+" 温度："+windDirection+" 风向："+windPower+" 风力："+reportTime+"发布时间："+temp;
        System.out.println("返回天气信息"+"日期:"+day+" 城市："+address+" 天气："+weather+" 温度："+windDirection+" 风向："+windPower+" 风力："+reportTime+"发布时间："+temp);
        return msg;
    }

}
