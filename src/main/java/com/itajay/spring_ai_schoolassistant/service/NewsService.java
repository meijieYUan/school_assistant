package com.itajay.spring_ai_schoolassistant.service;

import java.util.List;

/**
 * 新闻服务接口
 */
public interface NewsService {
    
    /**
     * 获取最新新闻
     * @param category 新闻分类 (可选: 科技、体育、财经、娱乐等)
     * @param limit 返回数量限制
     * @return 新闻列表
     */
    List<NewsItem> getLatestNews(String category, int limit);
    
    /**
     * 搜索新闻
     * @param keyword 搜索关键词
     * @param limit 返回数量限制
     * @return 新闻列表
     */
    List<NewsItem> searchNews(String keyword, int limit);
}