package com.itajay.spring_ai_schoolassistant.controller;

import com.itajay.spring_ai_schoolassistant.agent.InfoAgent;
import com.itajay.spring_ai_schoolassistant.agent.KnowledgeAgent;
import com.itajay.spring_ai_schoolassistant.agent.MainAgent;
import com.itajay.spring_ai_schoolassistant.agent.ScheduleAgent;
import com.itajay.spring_ai_schoolassistant.entity.Domain;
import com.itajay.spring_ai_schoolassistant.entity.RouteDecision;
import com.itajay.spring_ai_schoolassistant.entity.TaskDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assistant")
public class AssistantController {
    private final MainAgent mainAgent;

    @GetMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("message") String message, @RequestParam("chatId") String chatId) {
       return mainAgent.handle(message,chatId);
    }
}
