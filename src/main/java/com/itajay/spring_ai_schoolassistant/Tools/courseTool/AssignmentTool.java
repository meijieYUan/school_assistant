package com.itajay.spring_ai_schoolassistant.Tools.courseTool;

import com.itajay.spring_ai_schoolassistant.entity.po.Course;
import com.itajay.spring_ai_schoolassistant.entity.po.CourseAssignment;
import com.itajay.spring_ai_schoolassistant.entity.vo.AssignmentView;
import com.itajay.spring_ai_schoolassistant.service.CourseAssignmentService;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AssignmentTool {
    @Autowired
    private CourseAssignmentService courseAssignmentService;
    @Autowired
    private CourseService courseService;

    @Tool(name ="queryAssignmentByCourseName",description ="查询某课程的未完成作业" )
    public List<AssignmentView> queryAssignmentByCourseName(@ToolParam(description = "课程的名字") String courseName){
        return  courseAssignmentService.queryAssignmentsByCourseName(courseName);
    }

    @Tool(name ="queryPendingAssignments" ,description = "查询未完成的全部作业")
    public List<AssignmentView> queryPendingAssignments(@ToolParam(description="当前时间") LocalDateTime now){
        return courseAssignmentService.queryPendingAssignments(now);
    }
    @Tool(name = "queryOverdueAssignments",description = "查询已逾期的课程作业")
    public List<AssignmentView>queryOverdueAssignments(@ToolParam(description = "当前时间") LocalDateTime now){
        return courseAssignmentService.queryOverdueAssignments(now);
    }

    @Tool(name = "addAssignment",description = "新增课程作业")
    public String addAssignment(
           @ToolParam(description = "新增作业的截止时间") LocalDateTime deadline,
           @ToolParam(description = "新增作业的标题") String title,
           @ToolParam(description = "新增作业的详细描述及要求") String description,
           @ToolParam(description = "新增作业的课程名") String courseName
    ){
        Course course = courseService.lambdaQuery().eq(Course::getCourseName, courseName).one();
        if(course == null){
            return "新增失败，该课程不存在";
        }
        CourseAssignment courseAssignment = new CourseAssignment();
        courseAssignment.setDeadline(deadline);
        courseAssignment.setTitle(title);
        courseAssignment.setDescription(description);
        courseAssignment.setCourseId(course.getId());
        return courseAssignmentService.addAssignment(courseAssignment)?"新增成功":"新增失败，系统异常！";
    }

    @Tool(name = "queryFinishedAssignments",description = "查询已完成的全部课程作业")
    public List<AssignmentView> queryFinishedAssignments(){
        return courseAssignmentService.queryFinishedAssignments();
    }

    @Tool(name = "markFinished",description = "将课程作业标记完成")
    public String markFinished(@ToolParam(description = "课程作业的标题") String title){
       return courseAssignmentService.markFinished(title)?"标记完成":"标记失败，系统异常";
    }

}
