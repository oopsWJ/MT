package com.mt.api;


import com.mt.pojo.Schedule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;




@Component
@FeignClient(value = "mt-server-schedule")
public interface ScheduleApi {

    @RequestMapping("/selectScheduleByTime")
    Object selectScheduleByTime(String fId, String cId, String currentTime);

    @RequestMapping("/insertSchedule")
    Object insertSchedule(@RequestBody() List<Schedule> scheduleList);

    @RequestMapping("/selectMinPrice")
    Object selectMinPriceByCinema(String cId);
}
