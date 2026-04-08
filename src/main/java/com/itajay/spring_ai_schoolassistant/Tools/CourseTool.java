package com.itajay.spring_ai_schoolassistant.Tools;


import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTool {

   private final ChatClient courseAdviceClient;


    private final CourseService courseService;
    public CourseTool(CourseService courseService,ChatModel model) {
        this.courseService = courseService;
        courseAdviceClient=ChatClient
                .builder(model)
                .defaultSystem(SystemConsistant.COURSE_ADVICE_PROMPT)
                .build();

    }

    //returnDirect = true 直接返回给前端
    @Tool(description = "查询学生某一周的星期几的课程安排,并基于查询的课程给予建议"
            ,name = "queryOneDayCourses"
    ,returnDirect = true)
    public List<CourseScheduleView> queryOnedayCourses(
            @ToolParam(description = "第几周，1表示第一周") Integer week,
            @ToolParam(description = "星期几，1表示周一，7表示周日") Integer dayOfWeek) {


        return courseService.findOneDayCourses(week, dayOfWeek);
    }

    @Tool(description = "查询学生某一周的全部课程安排，并基于查询的课程给予建议和提醒"
            ,name = "queryOneWeekCourses"
            ,returnDirect = true)
    public List<CourseScheduleView>  queryOneWeekCourses(
            @ToolParam(description = "第几周，1表示第一周") Integer week) {
        return courseService.findOneWeekCourses(week);
    }
}