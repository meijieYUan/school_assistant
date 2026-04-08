package com.itajay.spring_ai_schoolassistant.Tools;

import com.itajay.spring_ai_schoolassistant.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

@RequiredArgsConstructor
public class WeatherTool {

    private final WeatherService weatherService;

    @Tool(description = "查询天气",name = "queryWeather")
    public String queryWeather(
            @ToolParam(description = "查询天气") String city) {
        return weatherService.queryWeather(city,1);
    }
}
