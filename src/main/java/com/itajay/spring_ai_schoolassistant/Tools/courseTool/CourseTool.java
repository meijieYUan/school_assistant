package com.itajay.spring_ai_schoolassistant.Tools.courseTool;


import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTool {

    private final CourseService courseService;
    public CourseTool(CourseService courseService) {
        this.courseService = courseService;
    }

    //returnDirect = true 直接返回给前端
    @Tool(description = "查询学生某一周的星期几的课程安排"
            ,name = "queryOneDayCourses",returnDirect = true
    )
    public List<CourseScheduleView> queryOnedayCourses(
            @ToolParam(description = "第几周，1表示第一周") Integer week,
            @ToolParam(description = "星期几，1表示周一，7表示周日") Integer dayOfWeek) {

        return courseService.findOneDayCourses(week,dayOfWeek);

    }

    @Tool(description = "查询学生某一周的全部课程安排"
            ,name = "queryOneWeekCourses"
            ,returnDirect = true)
    public List<CourseScheduleView> queryOneWeekCourses(
            @ToolParam(description = "第几周，1表示第一周") Integer week) {
        return courseService.findOneWeekCourses(week);
    }
}