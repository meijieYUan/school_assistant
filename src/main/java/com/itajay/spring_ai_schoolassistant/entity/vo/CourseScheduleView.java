package com.itajay.spring_ai_schoolassistant.entity.vo;

import lombok.Data;

@Data
public class CourseScheduleView {
    private String courseName;
    private String teacherName;
    private Integer dayOfWeek;
    private Integer startSection;   //开始节次
    private Integer endSection;
    private Integer startWeek;
    private Integer endWeek;
    private String weekType;
    private String classroom;
}