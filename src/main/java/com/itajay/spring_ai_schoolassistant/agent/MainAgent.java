package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.entity.RouteDecision;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MainAgent {
    @Autowired
    @Qualifier("mainClient")
    private  ChatClient mainClient;
    public RouteDecision handle(String userMessage){
     return  mainClient.prompt()
                .user(userMessage)
                .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                .call()
                .entity(RouteDecision.class);
    }
}
