package com.demo.tianqi.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 天气接口工具
 **/
public class TQUtil {

    public static String getTQ(String city){
        Map<String,Object> formMap = new HashMap<>();
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
        String msg= "日期:"+day+" 城市："+address+" 天气："+weather+" 温度："+windDirection+" 风向："+windPower+" 风力："+reportTime+"发布时间："+temp;
        System.out.println("返回天气信息"+"日期:"+day+" 城市："+address+" 天气："+weather+" 温度："+windDirection+" 风向："+windPower+" 风力："+reportTime+"发布时间："+temp);
        return msg;
    }

}
