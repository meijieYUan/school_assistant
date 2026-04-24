# school_assistant

## 项目概述

学生辅助AI助手 - 基于Spring AI框架构建的智能校园助手系统，采用多Agent架构实现智能对话和任务处理。

---

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.5.13 | 基础框架 |
| Spring AI | 1.1.4 | AI能力框架 |
| MyBatis-Plus | 3.5.14 | ORM框架 |
| MySQL | - | 关系型数据库 |
| Qdrant | - | 向量数据库(RAG) |
| OpenAI | - | 大语言模型 |

---

## 项目架构

```
spring_ai_SchoolAssistant/
├── agent/                    # AI Agent层
│   ├── MainAgent.java       # 主Agent - 对话入口
│   ├── InfoAgent.java       # 信息Agent - 天气/新闻查询
│   ├── ScheduleAgent.java   # 日程Agent - 课程/作业查询
│   └── KnowledgeAgent.java  # 知识Agent - RAG检索问答
├── controller/              # 控制器层
│   ├── AssistantController.java    # 对话接口
│   ├── KnowledgeController.java   # 知识库管理
│   └── TasklistController.java   # 任务清单管理
├── service/                 # 业务服务层
│   ├── CourseService.java           # 课程服务
│   ├── CourseAssignmentService.java # 作业服务
│   └── TaskListService.java         # 任务服务
├── entity/                  # 数据实体
│   ├── po/                 # 持久化对象
│   │   ├── Course.java           # 课程表
│   │   ├── CourseAssignment.java # 作业表
│   │   ├── CourseSchedule.java   # 课程安排表
│   │   └── TaskList.java         # 任务清单表
│   └── vo/                 # 视图对象
├── Tools/                   # 工具层
│   ├── courseTool/        # 课程相关工具
│   │   ├── CourseTool.java      # 查询课程
│   │   ├── AssignmentTool.java  # 管理作业
│   │   └── TaskTool.java        # 管理任务
│   ├── timeTool/          # 时间工具
│   │   └── TimeTool.java        # 获取当前时间
│   └── wetherTool/        # 天气工具
│       └── WeatherTool.java     # 查询天气
├── config/                 # 配置层
│   └── ChatClientAutoConfiguration.java  # ChatClient配置
└── consistant/             # 常量层
    └── SystemConsistant.java    # 系统常量定义
```

---

## 核心功能

### 1. 多Agent对话系统

项目采用**Supervisor-Agent模式**，由主Agent统一调度子Agent：

```
用户消息 → MainAgent(主调度)
           ├── InfoAgent(天气/新闻)
           ├── ScheduleAgent(课程/作业/任务)
           └── KnowledgeAgent(RAG知识库)
```

- **MainAgent**: 对话入口，负责意图识别和任务分发
- **InfoAgent**: 查询天气、新闻等实时信息
- **ScheduleAgent**: 管理课程表、作业、任务清单
- **KnowledgeAgent**: 基于RAG的专业知识问答

### 2. RAG知识库检索

使用Spring AI RAG框架实现检索增强：

```java
// 查询转换器 - 重写用户问题
RewriteQueryTransformer rewriteQueryTransformer = ...

// 向量存储检索器
VectorStoreDocumentRetriever retriever = ...

// 构建RAG顾问
RetrievalAugmentationAdvisor ragAdvisor = ...
```

**特性**:
- 支持PDF、Word、Markdown等文档格式
- 使用Qdrant向量数据库存储嵌入
- 相似度阈值: 0.6, TopK: 3

### 3. 课程与作业管理

| 工具 | 功能 |
|------|------|
| CourseTool | 查询单日/单周课程安排 |
| AssignmentTool | 查询/添加课程作业 |
| TaskTool | 管理待办任务 |

### 4. 天气查询

集成Open-Meteo免费天气API:
- 地理解析 → 获取经纬度 → 查询天气预报

### 5. 对话记忆

使用MySQL存储对话历史:
- `JdbcChatMemoryRepository` + 自定义MySQL方言
- `MessageWindowChatMemory` 实现滑动窗口记忆

---

## API接口

| 接口 | 方法 | 参数 | 说明 |
|------|------|------|------|
| `/assistant/chat` | GET | message, chatId | 流式对话 |

---

## 数据表

### course (课程表)
- id, courseName, teacherName

### course_assignment (作业表)
- id, courseId, title, description, deadline, status, createTime, updateTime

### task_list (任务清单)
- id, title, description, type, priority, status, deadline, completeTime

---

## 配置说明

主要配置在 `application.yaml`:
- 数据库连接
- Spring AI模型配置
- 向量数据库配置

---

## 运行要求

- JDK 17+
- MySQL 5.7+
- Maven 3.6+
