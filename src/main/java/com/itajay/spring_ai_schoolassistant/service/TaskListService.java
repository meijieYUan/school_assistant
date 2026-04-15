package com.itajay.spring_ai_schoolassistant.service;

import com.itajay.spring_ai_schoolassistant.entity.po.TaskList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itajay.spring_ai_schoolassistant.entity.vo.TaskView;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Joy
* @description 针对表【task_list(用户任务清单表)】的数据库操作Service
* @createDate 2026-04-10 14:44:34
*/
public interface TaskListService extends IService<TaskList> {
    //查询代办任务
    List<TaskView> queryPendingTasks(LocalDateTime now);

    //新增任务
    boolean InsertTasks(TaskList task);

    //查询已完成任务
    public List<TaskView> queryFinishedTaskList();

    //查询逾期任务
    public List<TaskView> queryOverdueTask(LocalDateTime now );

    //标记任务完成
    public boolean markFinished(String title);
}
