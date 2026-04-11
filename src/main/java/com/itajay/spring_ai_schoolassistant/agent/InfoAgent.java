package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.Tools.wetherTool.WeatherTool;
import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfoAgent {
    @Autowired
    @Qualifier("infoClient")
    private ChatClient infoClient;

    @Tool(name = "infoAgent",description = SystemConsistant.INFO_TOOL_DESCIPTION)
    public String handle(@ToolParam(description = "委托给子agent的任务描述信息") String taskDescription){
        log.debug("接受主agent的任务："+taskDescription);
        return infoClient.prompt()
                .user(taskDescription)
                .call()
                .content();
    }
}
