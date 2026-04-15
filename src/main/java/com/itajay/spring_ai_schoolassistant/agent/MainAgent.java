package com.itajay.spring_ai_schoolassistant.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MainAgent {
    @Autowired
    @Qualifier("mainClient")
    private ChatClient mainClient;

   /* public RouteDecision routing(String userMessage,String chatId){
     return  mainClient.prompt()
                .user(userMessage)
                .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
             .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(RouteDecision.class);
    }*/
   HashMap<String,Object> toolcontext = new HashMap<>();
    public Flux<String> handle(String userMessage,String chatId){
        toolcontext.put("beginning",LocalDate.of(2026,3,1));
       return mainClient.prompt()
               .user(userMessage)
               .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
               .toolContext(toolcontext)
                .stream()
                .content();
    }
}
