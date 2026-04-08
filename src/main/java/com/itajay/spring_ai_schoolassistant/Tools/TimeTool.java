package com.itajay.spring_ai_schoolassistant.Tools;



import org.springframework.ai.tool.annotation.Tool;

import java.time.ZonedDateTime;
public class TimeTool {

    @Tool(description = "获取当前时间，包括当前时区")
    public ZonedDateTime getCurrentTimeAndZone() {
        return ZonedDateTime.now();
    }
}
