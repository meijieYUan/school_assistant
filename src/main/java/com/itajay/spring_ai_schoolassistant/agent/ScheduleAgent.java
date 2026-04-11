package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.Tools.courseTool.CourseTool;
import com.itajay.spring_ai_schoolassistant.entity.po.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAgent {
    @Autowired
    @Qualifier("scheduleClient")
    private ChatClient scheduleClient;
    @Autowired
    private  CourseTool courseTool;
    public String handle(String userMessage) {
        return scheduleClient.prompt()
                .user(userMessage)
                .tools(courseTool)
                .call()
                .content();
    }
}
