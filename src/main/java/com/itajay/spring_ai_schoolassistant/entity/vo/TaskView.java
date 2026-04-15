package com.itajay.spring_ai_schoolassistant.entity.vo;

import java.time.LocalDateTime;

public class TaskView {
    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务详细描述
     */
    private String description;

    /**
     * 任务类型：assignment(作业)、exam(考试)、project(项目)、reminder(提醒)、other(其他)
     */
    private String type;
    /**
     * 任务状态：0-待办，1-已完成，2-已逾期
     */
    private Integer status;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 实际完成时间
     */
    private LocalDateTime completeTime;

}
