package com.itajay.spring_ai_schoolassistant.service.impl;

import com.itajay.spring_ai_schoolassistant.service.NewsItem;
import com.itajay.spring_ai_schoolassistant.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻服务实现 - 使用免费新闻API
 */
@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final RestClient newsClient = RestClient.builder()
            .baseUrl("https://gnews.io/api/v4")
            .build();

    @Value("${news.api.key:}")
    private String apiKey;

    @Override
    public List<NewsItem> getLatestNews(String category, int limit) {
        log.info("获取最新新闻, category: {}, limit: {}", category, limit);
        
        try {
            // 使用GNews免费API (需要配置API Key)
            if (apiKey == null || apiKey.isEmpty()) {
                log.warn("未配置新闻API Key，返回模拟数据");
                return getMockNews(category, limit);
            }
            
            String endpoint = category != null && !category.isEmpty() 
                    ? "/top-headlines?category=" + category + "&lang=zh&max=" + limit
                    : "/top-headlines?lang=zh&max=" + limit;
            
            GNewsResponse response = newsClient.get()
                    .uri(endpoint)
                    .header("Authorization", "Bearer " + apiKey)
                    .retrieve()
                    .body(GNewsResponse.class);
            
            if (response != null && response.getArticles() != null) {
                return convertToNewsItems(response.getArticles());
            }
        } catch (Exception e) {
            log.error("获取新闻失败: {}", e.getMessage());
        }
        
        // 失败时返回模拟数据
        return getMockNews(category, limit);
    }

    @Override
    public List<NewsItem> searchNews(String keyword, int limit) {
        log.info("搜索新闻, keyword: {}, limit: {}", keyword, limit);
        
        try {
            if (apiKey == null || apiKey.isEmpty()) {
                return getMockSearchNews(keyword, limit);
            }
            
            GNewsResponse response = newsClient.get()
                    .uri("/search?q=" + keyword + "&lang=zh&max=" + limit)
                    .header("Authorization", "Bearer " + apiKey)
                    .retrieve()
                    .body(GNewsResponse.class);
            
            if (response != null && response.getArticles() != null) {
                return convertToNewsItems(response.getArticles());
            }
        } catch (Exception e) {
            log.error("搜索新闻失败: {}", e.getMessage());
        }
        
        return getMockSearchNews(keyword, limit);
    }

    private List<NewsItem> convertToNewsItems(List<GNewsArticle> articles) {
        List<NewsItem> newsItems = new ArrayList<>();
        for (GNewsArticle article : articles) {
            NewsItem item = new NewsItem();
            item.setTitle(article.getTitle());
            item.setContent(article.getDescription());
            item.setSource(article.getSource().getName());
            item.setUrl(article.getUrl());
            item.setCategory(article.getSource().getName());
            // 解析ISO格式时间
            if (article.getPublishedAt() != null) {
                try {
                    item.setPublishTime(LocalDateTime.parse(article.getPublishedAt(), ISO_FORMATTER));
                } catch (Exception e) {
                    item.setPublishTime(LocalDateTime.now());
                }
            } else {
                item.setPublishTime(LocalDateTime.now());
            }
            newsItems.add(item);
        }
        return newsItems;
    }

    // 模拟数据 - 当API不可用时
    private List<NewsItem> getMockNews(String category, int limit) {
        List<NewsItem> items = new ArrayList<>();
        String[] titles = {
            "教育部发布2026年高校招生新政策",
            "科技创新引领高质量发展论坛在京举行",
            "新能源汽车销量突破1000万辆",
            "人工智能技术取得重大突破",
            "体育赛事全面复苏，观众人数创新高"
        };
        
        for (int i = 0; i < Math.min(limit, titles.length); i++) {
            NewsItem item = new NewsItem();
            item.setTitle(titles[i]);
            item.setContent("近日，" + titles[i] + "相关报道持续引发关注...");
            item.setSource("模拟新闻源");
            item.setCategory(category != null ? category : "综合");
            item.setPublishTime(LocalDateTime.now().minusHours(i));
            items.add(item);
        }
        return items;
    }

    private List<NewsItem> getMockSearchNews(String keyword, int limit) {
        List<NewsItem> items = new ArrayList<>();
        NewsItem item = new NewsItem();
        item.setTitle("关于" + keyword + "的最新报道");
        item.setContent("搜索关键词「" + keyword + "」相关的最新新闻资讯...");
        item.setSource("模拟新闻源");
        item.setCategory("搜索结果");
        item.setPublishTime(LocalDateTime.now());
        items.add(item);
        return items;
    }

    // GNews API 响应类
    @lombok.Data
    static class GNewsResponse {
        private int totalArticles;
        private List<GNewsArticle> articles;
    }

    @lombok.Data
    static class GNewsArticle {
        private String title;
        private String description;
        private String content;
        private String url;
        private String image;
        private String publishedAt;
        private GNewsSource source;
    }

    @lombok.Data
    static class GNewsSource {
        private String name;
        private String url;
    }
}