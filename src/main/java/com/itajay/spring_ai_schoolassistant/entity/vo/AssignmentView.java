package com.itajay.spring_ai_schoolassistant.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AssignmentView {

    /**
     * 作业的课程名
     */
    private String CourseName;
    /**
     * 作业标题
     */
    private String title;

    /**
     * 作业详细描述和要求
     */

    private String description;

    /**
     * 提交截止时间
     */

    private LocalDateTime deadline;

    /**
     * '作业状态：0-待完成，1-已完成，2-已逾期'
     */
    private Integer status;

}
