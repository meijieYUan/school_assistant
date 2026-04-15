package com.itajay.spring_ai_schoolassistant.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户任务清单表
 * @TableName task_list
 */
@TableName(value ="task_list")
public class TaskList {
    /**
     * 任务ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 优先级：1-高，2-中，3-低
     */
    private Integer priority;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 任务ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 任务ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 任务标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 任务标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 任务详细描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 任务详细描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 任务类型：assignment(作业)、exam(考试)、project(项目)、reminder(提醒)、other(其他)
     */
    public String getType() {
        return type;
    }

    /**
     * 任务类型：assignment(作业)、exam(考试)、project(项目)、reminder(提醒)、other(其他)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 优先级：1-高，2-中，3-低
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 优先级：1-高，2-中，3-低
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 任务状态：0-待办，1-进行中，2-已完成，3-已逾期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 任务状态：0-待办，1-进行中，2-已完成，3-已逾期
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 截止时间
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * 截止时间
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * 实际完成时间
     */
    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    /**
     * 实际完成时间
     */
    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskList other = (TaskList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getCompleteTime() == null ? other.getCompleteTime() == null : this.getCompleteTime().equals(other.getCompleteTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getCompleteTime() == null) ? 0 : getCompleteTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", priority=").append(priority);
        sb.append(", status=").append(status);
        sb.append(", deadline=").append(deadline);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}