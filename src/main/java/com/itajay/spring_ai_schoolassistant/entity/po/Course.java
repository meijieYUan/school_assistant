package com.itajay.spring_ai_schoolassistant.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("course")
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String courseName;     // 课程名称
    private String teacherName;    // 教师姓名
}