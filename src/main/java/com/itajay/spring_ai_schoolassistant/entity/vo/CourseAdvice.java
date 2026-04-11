package com.itajay.spring_ai_schoolassistant.entity.vo;

import lombok.Data;

import java.util.List;
@Data
public class CourseAdvice {
   public  List<CourseScheduleView>    courseDetails;
    public String advice;
}
