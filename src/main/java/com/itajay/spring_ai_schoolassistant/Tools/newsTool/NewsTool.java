package com.itajay.spring_ai_schoolassistant.Tools.newsTool;

import com.itajay.spring_ai_schoolassistant.service.NewsItem;
import com.itajay.spring_ai_schoolassistant.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 新闻工具 - 提供给InfoAgent使用
 */
@Slf4j
@Component
public class NewsTool {

    private final NewsService newsService;

    public NewsTool(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * 获取最新新闻列表
     */
    @Tool(name = "getLatestNews", description = "获取最新新闻列表，可按分类筛选", returnDirect = true)
    public List<NewsItem> getLatestNews(
            @ToolParam(description = "新闻分类: 科技(technology)、体育(sports)、商业(business)、\n" +
                    "娱乐(entertainment)、健康(health)、科学(science)、综合(general)。不填则返回综合新闻") String category,
            @ToolParam(description = "返回新闻数量，默认5条") Integer limit) {
        log.info("收到获取新闻请求, category: {}, limit: {}", category, limit);
        int newsLimit = (limit != null && limit > 0) ? limit : 5;
        return newsService.getLatestNews(category, newsLimit);
    }

    /**
     * 搜索新闻
     */
    @Tool(name = "searchNews", description = "根据关键词搜索新闻", returnDirect = true)
    public List<NewsItem> searchNews(
            @ToolParam(description = "搜索关键词") String keyword,
            @ToolParam(description = "返回新闻数量，默认5条") Integer limit) {
        log.info("收到搜索新闻请求, keyword: {}, limit: {}", keyword, limit);
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        int newsLimit = (limit != null && limit > 0) ? limit : 5;
        return newsService.searchNews(keyword, newsLimit);
    }
}