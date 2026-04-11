package com.itajay.spring_ai_schoolassistant.Tools.wetherTool;

import org.springframework.ai.tool.annotation.ToolParam;

// 天气请求参数
public record WetherRequest(@ToolParam(description = "查询的城市，例如beijing,shanghai") String city,@ToolParam(description = "查询的近期天数，例如1表示当天，3表示今后三天") Integer days) { }
