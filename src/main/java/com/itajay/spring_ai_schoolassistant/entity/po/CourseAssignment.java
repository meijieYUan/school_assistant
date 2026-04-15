package com.itajay.spring_ai_schoolassistant.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 课程作业表
 * @TableName course_assignment
 */
@TableName(value ="course_assignment")
public class CourseAssignment {
    /**
     * 作业ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属课程ID
     */
    private Integer courseId;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 作业ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 作业ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属课程ID
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * 所属课程ID
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * 作业标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 作业标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作业详细描述和要求
     */
    public String getDescription() {
        return description;
    }

    /**
     * 作业详细描述和要求
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 提交截止时间
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * 提交截止时间
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * 作业状态：1-未发布，2-已发布，3-已结束
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 作业状态：1-未发布，2-已发布，3-已结束
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        CourseAssignment other = (CourseAssignment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", courseId=").append(courseId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", deadline=").append(deadline);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}