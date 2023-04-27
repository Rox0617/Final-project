package com.demo.tianqi.quartz;

import com.demo.tianqi.util.TQUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc 定时获取天气情况
 **/
@Service
public class TimeGetTQ {

    public ScheduledExecutorService timerDatReport = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("timerDataReport").daemon(true).build());

    //每隔多长时间执行一次
    long timeInterval =  30 * 1000;
    //项目启动后多长时间执行
    long delayTime = 10 * 1000;

    @PostConstruct
    public void init(){
        timerDatReport.scheduleAtFixedRate(new GetTQDataExecutor(),delayTime,timeInterval, TimeUnit.MILLISECONDS);
    }
    class GetTQDataExecutor implements Runnable{
        @Override
        public void run(){

//            String tianqi = TQUtil.getTQ("上海市");
//            System.out.println("定时获取天气:"+tianqi);


        }
    }
}
