package com.itajay.spring_ai_schoolassistant.controller;

import com.itajay.spring_ai_schoolassistant.agent.InfoAgent;
import com.itajay.spring_ai_schoolassistant.agent.KnowledgeAgent;
import com.itajay.spring_ai_schoolassistant.agent.MainAgent;
import com.itajay.spring_ai_schoolassistant.agent.ScheduleAgent;
import com.itajay.spring_ai_schoolassistant.entity.Domain;
import com.itajay.spring_ai_schoolassistant.entity.RouteDecision;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assistant")
/*
    暴露给用户的唯一接口
 */
public class AssistantController {
/*
    private final CourseTool courseTool;
    private final ChatClient chatClient;
    @GetMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("message") String message,@RequestParam("chatId") String chatId) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(advisor->advisor.param(ChatMemory.CONVERSATION_ID,chatId))
                //.advisors(advisorSpec -> advisorSpec.param(QuestionAnswerAdvisor.FILTER_EXPRESSION,"source=='LeetCode经典面试题150解题思路总结.pdf'"))
                .tools(courseTool)
                .stream()
                .content();

    }*/
    private final MainAgent mainAgent;
    private final InfoAgent infoAgent;
    private final KnowledgeAgent knowledgeAgent;
    private final ScheduleAgent scheduleAgent;

    @GetMapping(value = "/chat",produces = "text/markdown;charset=utf-8")
    public String chat(@RequestParam("message") String message,@RequestParam("chatId") String chatId) {
        RouteDecision routeDecision = mainAgent.handle(message);
        if(routeDecision.domains()==null||routeDecision.domains().isEmpty()){
            return routeDecision.finalAnswer()!=null?routeDecision.finalAnswer():"服务器繁忙。。。";
        }
        StringBuilder answer = new StringBuilder();
        for(Domain domain: routeDecision.domains()){
            String answer_clip=switch(domain){
                case SCHEDULE -> scheduleAgent.handle(message);
                case KNOWLEDGE -> knowledgeAgent.handle(message);
                case INFO -> infoAgent.handle(message);

            };
            answer.append(answer_clip);
        }
       return answer.toString();
    }
}
