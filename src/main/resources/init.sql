create schema if not exists school_assistant;
use school_assistant;

-- Spring AI Chat 记忆数据库初始化脚本
CREATE TABLE IF NOT EXISTS chat_memory
(
    conversation_id VARCHAR(36) NOT NULL,
    type            enum ('USER', 'ASSISTANT', 'SYSTEM', 'TOOL'),
    content         TEXT        NOT NULL,
    timestamp       TIMESTAMP   NOT NULL,
    INDEX idx_conversation_id (conversation_id)
);
-- 课程表
CREATE TABLE IF NOT EXISTS course
(
    id           int PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
    course_name  VARCHAR(128) NOT NULL COMMENT '课程名称',
    teacher_name VARCHAR(128) COMMENT '教师名称'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '课程信息表';
drop table if exists course_schedule;
-- 课程安排表
CREATE TABLE IF NOT EXISTS course_schedule
(
    id            int PRIMARY KEY AUTO_INCREMENT COMMENT '课程安排ID',
    course_id     int                          NOT NULL COMMENT '课程ID',
    day_of_week   TINYINT                      NOT NULL COMMENT '星期几：1-7（1=周一，7=周日）',
    start_section TINYINT                      NOT NULL COMMENT '开始节次',
    end_section   TINYINT                      NOT NULL COMMENT '结束节次',
    start_week    INT                          NOT NULL COMMENT '开始周',
    end_week      INT                          NOT NULL COMMENT '结束周',
    week_type     ENUM ('EVERY','ODD', 'EVEN') NOT NULL COMMENT '每周(EVERY) / 奇数周(ODD) / 偶数周(EVEN)',
    classroom     VARCHAR(128)                 NOT NULL COMMENT '教室',
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    INDEX idx_course_id (course_id),
    INDEX idx_week_range (start_week, end_week),
    CONSTRAINT chk_section CHECK (end_section >= start_section),
    CONSTRAINT chk_week CHECK (start_week <= end_week AND start_week >= 1 AND end_week <= 53)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '课程排课表';

-- 课程表数据
INSERT INTO course (id, course_name, teacher_name)
VALUES (1, '高等数学', '张老师'),
       (2, '大学英语', '李老师'),
       (3, 'Java程序设计', '王老师'),
       (4, '数据结构', '赵老师'),
       (5, '操作系统', '陈老师'),
       (6, '计算机网络', '孙老师'),
       (7, '数据库原理', '周老师'),
       (8, '人工智能导论', '吴老师'),
       (9, '软件工程', '刘老师'),
       (10, '离散数学', '黄老师'),
       (11, 'Python程序设计', '何老师'),
       (12, '计算机组成原理实验', '杨老师');

-- 课程安排表数据
INSERT INTO course_schedule
(id, course_id, day_of_week, start_section, end_section, start_week, end_week, week_type, classroom)
VALUES
-- =========================
-- 周一
-- 高等数学：整学期，每周
(1, 1, 1, 1, 2, 1, 16, 'EVERY', '教学楼A101'),

-- Java程序设计：前半学期，每周
(2, 3, 1, 3, 4, 1, 8, 'EVERY', '实验楼B203'),

-- 大学英语：整学期，单周
(3, 2, 1, 7, 8, 1, 16, 'ODD', '教学楼C302'),

-- =========================
-- 周二
-- 数据结构：整学期，每周
(4, 4, 2, 1, 2, 1, 16, 'EVERY', '教学楼A202'),

-- 数据库原理：后半学期，每周
(5, 7, 2, 5, 6, 9, 16, 'EVERY', '信息楼401'),

-- 离散数学：前半学期，双周
(6, 10, 2, 7, 8, 1, 8, 'EVEN', '教学楼D201'),

-- =========================
-- 周三
-- 操作系统：后半学期，每周
(7, 5, 3, 3, 4, 9, 16, 'EVERY', '教学楼D105'),

-- 人工智能导论：后半学期，双周
(8, 8, 3, 7, 8, 10, 16, 'EVEN', '教学楼E201'),

-- Python程序设计：前半学期，每周
(9, 11, 3, 1, 2, 1, 8, 'EVERY', '实验楼C105'),

-- =========================
-- 周四
-- 计算机网络：整学期，每周
(10, 6, 4, 1, 2, 1, 16, 'EVERY', '网络实验室301'),

-- Java程序设计：前半学期，每周
(11, 3, 4, 5, 6, 1, 8, 'EVERY', '实验楼B203'),

-- 软件工程：后半学期，单周
(12, 9, 4, 7, 8, 9, 16, 'ODD', '教学楼F101'),

-- =========================
-- 周五
-- 高等数学：整学期，每周
(13, 1, 5, 1, 2, 1, 16, 'EVERY', '教学楼A101'),

-- 数据库原理：后半学期，每周
(14, 7, 5, 3, 4, 9, 16, 'EVERY', '信息楼401'),

-- 大学英语：整学期，每周
(15, 2, 5, 7, 8, 1, 16, 'EVERY', '教学楼C302'),

-- =========================
-- 周六
-- 计算机组成原理实验：仅 5-12 周，每周
(16, 12, 6, 1, 4, 5, 12, 'EVERY', '硬件实验室201'),

-- 人工智能导论：后半学期，双周
(17, 8, 6, 5, 6, 10, 16, 'EVEN', '教学楼E202'),

-- 软件工程：后半学期，每周
(18, 9, 6, 7, 8, 11, 16, 'EVERY', '教学楼F102');

drop table course_assignment;
CREATE TABLE IF NOT EXISTS course_assignment
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '作业ID',
    course_id   INT          NOT NULL COMMENT '所属课程ID',
    title       VARCHAR(255) NOT NULL COMMENT '作业标题',
    description TEXT COMMENT '作业详细描述和要求',
    deadline    DATETIME COMMENT '提交截止时间',
    status      TINYINT UNSIGNED DEFAULT 0 COMMENT '作业状态：0-待完成，1-已完成，2-已逾期',

    create_time DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    INDEX idx_course_id (course_id),
    INDEX idx_deadline (deadline),
    INDEX idx_status (status),

    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程作业表';

CREATE TABLE IF NOT EXISTS task_list
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '任务ID',
    title         VARCHAR(255) NOT NULL COMMENT '任务标题',
    description   TEXT COMMENT '任务详细描述',
    type          VARCHAR(32)      DEFAULT 'assignment' COMMENT '任务类型：assignment(作业)、exam(考试)、project(项目)、reminder(提醒)、other(其他)',

    priority      TINYINT UNSIGNED DEFAULT 2 COMMENT '优先级：1-高，2-中，3-低',
    status        TINYINT UNSIGNED DEFAULT 0 COMMENT '任务状态：0-待办，1-已完成，2-已逾期',

    deadline      DATETIME COMMENT '截止时间',
    complete_time DATETIME COMMENT '实际完成时间',

    create_time   DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    INDEX idx_status (status),
    INDEX idx_priority (priority),
    INDEX idx_deadline (deadline),
    INDEX idx_type (type)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户任务清单表';