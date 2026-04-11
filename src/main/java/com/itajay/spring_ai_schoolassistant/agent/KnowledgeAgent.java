package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *  RAG知识库检索问答agent
 */
@Slf4j
@Service
public class KnowledgeAgent {
    @Autowired
    @Qualifier("knowledgeClient")
    private ChatClient knowledgeClient;
    @Autowired
    private VectorStore vectorStore;
   @Autowired
   private ChatModel chatModel;
    @Tool(name = "knowledgeAgent",description = SystemConsistant.KNOWLEDGE_TOOL_DESCIPTION)
    public String handle(@ToolParam(description = "委托给子agent的任务描述信息") String taskDescription){
        log.debug("接受主agent的任务："+taskDescription);
        //调用LLM重写用户询问词  ---单轮重写型
        RewriteQueryTransformer rewriteQueryTransformer = RewriteQueryTransformer.builder()
                .chatClientBuilder(ChatClient.builder(chatModel).defaultOptions(ChatOptions.builder()
                .temperature(0.1)
                .build())).build();
        //创建RAG顾问
        RetrievalAugmentationAdvisor ragAdvisor = RetrievalAugmentationAdvisor.builder().queryTransformers(rewriteQueryTransformer)
                .documentRetriever(
                        //文档检索器
                        VectorStoreDocumentRetriever.builder()
                                .vectorStore(vectorStore).similarityThreshold(0.6)
                                .topK(3).build()).build();
        return knowledgeClient.prompt()
                .user(taskDescription)
                .advisors(ragAdvisor)
                .call()
                .content();
    }
}
