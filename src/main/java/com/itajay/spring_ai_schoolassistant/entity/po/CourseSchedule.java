package com.itajay.spring_ai_schoolassistant.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("course_schedule")
public class CourseSchedule {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long courseId;         // 对应哪门课

    private Integer dayOfWeek;     // 星期几：1-7（建议1=周一，7=周日）
    private Integer startSection;  // 开始节次
    private Integer endSection;    // 结束节次

    private Integer startWeek;     // 开始周
    private Integer endWeek;       // 结束周

    private String weekType;       //  ODD / EVEN
    private String classroom;      // 教室
}