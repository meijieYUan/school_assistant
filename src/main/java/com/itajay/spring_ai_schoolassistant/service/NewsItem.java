package com.itajay.spring_ai_schoolassistant.service;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 新闻条目
 */
@Data
public class NewsItem {
    private String title;           // 标题
    private String content;         // 内容摘要
    private String source;          // 来源
    private String url;             // 原文链接
    private String category;        // 分类
    private LocalDateTime publishTime; // 发布时间
}