package com.itajay.spring_ai_schoolassistant.Tools.timeTool;



import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
public class TimeTool {

    @Tool(description = "获取当前时间")
    public LocalDateTime getCurrentTimeAndZone() {
        return LocalDateTime.now();
    }
}
