package com.itajay.spring_ai_schoolassistant.controller;

import com.itajay.spring_ai_schoolassistant.agent.MainAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
