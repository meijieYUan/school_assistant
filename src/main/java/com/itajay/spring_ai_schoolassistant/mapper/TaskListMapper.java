package com.itajay.spring_ai_schoolassistant.mapper;

import com.itajay.spring_ai_schoolassistant.entity.po.TaskList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itajay.spring_ai_schoolassistant.entity.vo.TaskView;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Joy
* @description 针对表【task_list(用户任务清单表)】的数据库操作Mapper
* @createDate 2026-04-10 14:44:34
* @Entity com.itajay.spring_ai_schoolassistant.entity.po.TaskList
*/
public interface TaskListMapper extends BaseMapper<TaskList> {

    List<TaskView> queryPendingTasks(LocalDateTime now);


    List<TaskView> queryFinishedTaskList();

    List<TaskView> queryOverdueTask();
}




