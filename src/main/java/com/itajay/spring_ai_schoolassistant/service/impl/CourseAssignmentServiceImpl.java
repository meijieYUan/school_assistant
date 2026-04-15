package com.itajay.spring_ai_schoolassistant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itajay.spring_ai_schoolassistant.entity.po.CourseAssignment;
import com.itajay.spring_ai_schoolassistant.entity.vo.AssignmentView;
import com.itajay.spring_ai_schoolassistant.mapper.CourseAssignmentMapper;
import com.itajay.spring_ai_schoolassistant.service.CourseAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Joy
* @description 针对表【course_assignment(课程作业表)】的数据库操作Service实现
* @createDate 2026-04-10 14:42:01
*/
@Service
public class CourseAssignmentServiceImpl extends ServiceImpl<CourseAssignmentMapper, CourseAssignment>
    implements CourseAssignmentService{
    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    @Override
    public List<AssignmentView> queryAssignmentsByCourseName(String courseName) {
        return courseAssignmentMapper.queryAssignmentsByCourseName(courseName);
    }

    @Override
    public List<AssignmentView> queryPendingAssignments(LocalDateTime now) {
        return courseAssignmentMapper.queryPendingAssignments(now);

    }

    @Override
    public boolean addAssignment(CourseAssignment courseAssignment) {
        return save(courseAssignment);
    }

    @Override
    public List<AssignmentView> queryOverdueAssignments(LocalDateTime now) {
        lambdaUpdate().lt(CourseAssignment::getDeadline,LocalDateTime.now()).set(CourseAssignment::getStatus,2).update();
        return courseAssignmentMapper.queryOverdueAssignments();
    }

    @Override
    public List<AssignmentView> queryFinishedAssignments() {
        return courseAssignmentMapper.queryFinishedAssignments();
    }

    @Override
    public boolean markFinished(String title) {
      return lambdaUpdate().eq(CourseAssignment::getTitle, title).set(CourseAssignment::getStatus, 2).update();
    }
}




