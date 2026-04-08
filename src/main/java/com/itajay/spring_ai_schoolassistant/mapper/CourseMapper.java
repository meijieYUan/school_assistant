package com.itajay.spring_ai_schoolassistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itajay.spring_ai_schoolassistant.entity.po.Course;
import com.itajay.spring_ai_schoolassistant.entity.vo.CourseScheduleView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 查询指定周的星期几课程安排
     * @param week   第几周
     * @param dayOfWeek     当星期几
     * @return
     */
    List<CourseScheduleView> findOneDayCourses(Integer week, Integer dayOfWeek);

    List<CourseScheduleView> findOneWeekCourses(Integer week);
}
