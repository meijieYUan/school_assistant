package com.itajay.spring_ai_schoolassistant.controller;

import com.itajay.spring_ai_schoolassistant.Tools.CourseTool;
import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assistant")
public class AssistantController {

    //已自动配置
    private final VectorStore vectorStore;

    private final ChatClient chatClient;
    private final CourseService courseService;
    @GetMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("message") String message,@RequestParam("chatId") String chatId) {
        return chatClient
                .prompt()
                .system(SystemConsistant.SYSTEM_PROMPT)
                .user(message)
                .advisors(advisor->advisor.param(ChatMemory.CONVERSATION_ID,chatId))
                //.advisors(advisorSpec -> advisorSpec.param(QuestionAnswerAdvisor.FILTER_EXPRESSION,"source=='LeetCode经典面试题150解题思路总结.pdf'"))
                //.tools()
                .stream()
                .content();
    }

    @GetMapping("queryCourse")
    public List<CourseScheduleView> queryCourse(@RequestParam("week") Integer week){
        return courseService.findOneWeekCourses(week);
    }

    @GetMapping("/generateVector")
    public  String generateVector(@RequestParam("text") String text){
        vectorStore.add(List.of(Document.builder().text(text).metadata("文件名","八股面经.pdf").build()));
        return "已成功嵌入为向量！";
    }



}
