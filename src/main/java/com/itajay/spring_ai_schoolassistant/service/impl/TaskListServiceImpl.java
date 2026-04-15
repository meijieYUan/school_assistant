package com.itajay.spring_ai_schoolassistant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itajay.spring_ai_schoolassistant.entity.po.TaskList;
import com.itajay.spring_ai_schoolassistant.entity.vo.TaskView;
import com.itajay.spring_ai_schoolassistant.service.TaskListService;
import com.itajay.spring_ai_schoolassistant.mapper.TaskListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Joy
* @description 针对表【task_list(用户任务清单表)】的数据库操作Service实现
* @createDate 2026-04-10 14:44:34
*/
@Service
public class TaskListServiceImpl extends ServiceImpl<TaskListMapper, TaskList>
    implements TaskListService{
    @Autowired
    private TaskListMapper taskListMapper;
    @Override
    public List<TaskView> queryPendingTasks(LocalDateTime now) {
        return taskListMapper.queryPendingTasks(now);
    }

    @Override
    public boolean InsertTasks(TaskList task) {
        return save(task);
    }

    @Override
    public List<TaskView> queryFinishedTaskList() {
        return taskListMapper.queryFinishedTaskList();
    }

    @Override
    public List<TaskView> queryOverdueTask(LocalDateTime now) {
        lambdaUpdate().lt(TaskList::getDeadline,LocalDateTime.now()).set(TaskList::getStatus,2).update();
        return taskListMapper.queryOverdueTask();
    }

    @Override
    public boolean markFinished(String title) {
        return lambdaUpdate().eq(TaskList::getTitle, title).set(TaskList::getStatus, 1).update();
    }
}




