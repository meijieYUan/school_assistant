package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.Tools.courseTool.CourseTool;
import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import com.itajay.spring_ai_schoolassistant.entity.po.Course;
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
public class ScheduleAgent {
    @Autowired
    @Qualifier("scheduleClient")
    private ChatClient scheduleClient;
    @Tool(name = "scheduleAgent",description = SystemConsistant.SCHEDULE_TOOL_DESCIPTION)
    public String handle(@ToolParam(description="委托给子agent的任务描述信息") String taskDescription) {
        log.debug("接受主agent的任务："+taskDescription);
        return scheduleClient.prompt()
                .user(taskDescription)
                .call()
                .content();
    }
}
