package com.itajay.spring_ai_schoolassistant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itajay.spring_ai_schoolassistant.entity.po.Course;
import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import com.itajay.spring_ai_schoolassistant.mapper.CourseMapper;
import com.itajay.spring_ai_schoolassistant.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseMapper courseMapper;
    @Override
    public  List<CourseScheduleView>  findOneDayCourses(Integer week, Integer dayOfWeek) {
        return  courseMapper.findOneDayCourses(week, dayOfWeek);
    }

    @Override
    public  List<CourseScheduleView>  findOneWeekCourses(Integer week) {
        return courseMapper.findOneWeekCourses(week);
    }

}
