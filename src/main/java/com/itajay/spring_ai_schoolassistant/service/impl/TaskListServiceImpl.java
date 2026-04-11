package com.itajay.spring_ai_schoolassistant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itajay.spring_ai_schoolassistant.entity.po.TaskList;
import com.itajay.spring_ai_schoolassistant.service.TaskListService;
import com.itajay.spring_ai_schoolassistant.mapper.TaskListMapper;
import org.springframework.stereotype.Service;

/**
* @author Joy
* @description 针对表【task_list(用户任务清单表)】的数据库操作Service实现
* @createDate 2026-04-10 14:44:34
*/
@Service
public class TaskListServiceImpl extends ServiceImpl<TaskListMapper, TaskList>
    implements TaskListService{

}




