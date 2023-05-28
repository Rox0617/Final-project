package com.demo.city.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 获取全国城市 Acquire national cities
 **/
@Controller
@RequestMapping("/city")
public class CityController {

    @RequestMapping("/getCity")
    @ResponseBody
    public JSONObject getAllCity(){
        JSONObject jsonObject = new JSONObject();
        String APP_ID = "mpvwgwmlsdkignqy";
        String APP_SECRET = "RDhEektTMVFVT1JzOE5YT0RXNlIrQT09";
        /**
         * 获取全国城市接口 Get the national city interface
         * https://www.mxnzp.com/api/address/list?app_id=不再提供请自主申请&app_secret=不再提供请自主申请
         */
        String url="https://www.mxnzp.com/api/address/list?app_id="+APP_ID+"&app_secret="+APP_SECRET;
        //链式构建请求 Chain build request
        Map<String,Object> formMap = new HashMap<>();
        String result = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                                                               // Header information
                                                               // multiple header information can be called multiple times this method
                .form(formMap)//表单内容 form properties
                .execute().body();
        JSONObject resJson = JSONObject.fromObject(result);
        JSONArray jsonArray = resJson.getJSONArray("data");
        if(jsonArray.size()>0){
            //循环获取城市名称存入jsonObject里面
            // Loop the city name into the jsonObject
            for(int i = 0;i<jsonArray.size();i++){
            jsonObject.put("name"+i+1,jsonArray.getJSONObject(i).get("name").toString());
            }
        }
        return jsonObject;
    }
}
