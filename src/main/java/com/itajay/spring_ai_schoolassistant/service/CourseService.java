package com.itajay.spring_ai_schoolassistant.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.itajay.spring_ai_schoolassistant.entity.po.Course;
import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService extends IService<Course> {

    List<CourseScheduleView>  findOneDayCourses(@Param("week") Integer week, @Param("dayOfWeek") Integer dayOfWeek);

    List<CourseScheduleView>  findOneWeekCourses(@Param("week")Integer week);
}
