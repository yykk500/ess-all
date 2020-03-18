package com.ess.example.goods.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkJobSchedule {

//    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        System.out.println("执行定时任务..");
    }
}
