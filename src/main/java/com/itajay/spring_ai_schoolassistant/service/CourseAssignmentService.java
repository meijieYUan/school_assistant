package com.itajay.spring_ai_schoolassistant.service;

import com.itajay.spring_ai_schoolassistant.entity.po.CourseAssignment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itajay.spring_ai_schoolassistant.entity.vo.AssignmentView;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Joy
* @description 针对表【course_assignment(课程作业表)】的数据库操作Service
* @createDate 2026-04-10 14:42:01
*/
public interface CourseAssignmentService extends IService<CourseAssignment> {
    //查询相关课程的作业
    List<AssignmentView>queryAssignmentsByCourseName(String courseName);

    //查询所有待完成作业
    List<AssignmentView>queryPendingAssignments(LocalDateTime now);

    //新增课程作业
    boolean addAssignment(CourseAssignment courseAssignment);

    //查询逾期作业
    List<AssignmentView>queryOverdueAssignments(LocalDateTime now);

    //查询已完成作业
    List<AssignmentView>queryFinishedAssignments();

    //标记已完成作业
    public boolean  markFinished(String title);
}
