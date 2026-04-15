package com.itajay.spring_ai_schoolassistant.Tools.courseTool;

import com.itajay.spring_ai_schoolassistant.entity.po.TaskList;
import com.itajay.spring_ai_schoolassistant.entity.vo.TaskView;
import com.itajay.spring_ai_schoolassistant.service.TaskListService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskTool {

    @Autowired
    private TaskListService taskListService;

    @Tool(name = "queryPendingTasks",description = "查询待办的任务")
    public List<TaskView> queryPendingTasks(){
        return taskListService.queryPendingTasks(LocalDateTime.now());
    }

    @Tool(name = "queryFinishedTaskList",description = "查询已完成的任务")
    public List<TaskView> queryFinishedTaskList(){
        return taskListService.queryFinishedTaskList();
    }
    @Tool(name = "queryOverdueTask",description = "查询已逾期的任务")
    public List<TaskView>queryOverdueTask(){
        return taskListService.queryOverdueTask(LocalDateTime.now());
    }

    @Tool(name = "markFinshed",description = "将任务标记为已完成")
    public String markFinshed(@ToolParam(description = "任务的标题") String title){
       return taskListService.markFinished(title)?"标记完成":"标记失败，系统异常";
    }

    @Tool(name = "addTask",description = "添加待办任务")
    public String addTask(@ToolParam(description = "任务的标题信息") String title,
                          @ToolParam(description = "任务的描述信息及要求") String description,
                          @ToolParam(description = "任务的类型") String type,
                          @ToolParam(description = "任务的截止日期") LocalDateTime deadline,
                          @ToolParam(description = "任务的优先级",required = false) Integer priority){
        TaskList taskList = new TaskList();
        taskList.setTitle(title);
        taskList.setDescription(description);
        taskList.setType(type);
        taskList.setDeadline(deadline);
        if(priority!=null){taskList.setPriority(priority);}
        return taskListService.save(taskList)?"添加成功":"添加失败，系统异常";
    }

}
