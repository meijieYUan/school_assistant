package com.itajay.spring_ai_schoolassistant.service.impl;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final VectorStore vectorStore;

    public DocumentService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void EmbeddingDocument(Resource resource){
        /*
        ExtractedTextFormatter textFormatter 的作用，简单说就是：
        在 TikaDocumentReader 把文档内容封装成 Document 之前，先对 Tika 抽取出来的原始文本做一次清洗和格式整理。
        左对齐文本
        删除开头若干行
        删除结尾若干行
        合并连续空行      默认内部静态方法实现
         */
        //原文档
        ExtractedTextFormatter fromatter = ExtractedTextFormatter.builder().overrideLineSeparator("\n").build();
        List<Document> original = new TikaDocumentReader(resource,fromatter).get();
        //文档划分器
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(1000)       //token计数
                .withMinChunkSizeChars(400)
                .withKeepSeparator(true)
                .withPunctuationMarks(List.of('。', '？', '！'))
                .build();
        List<Document> splitted_documents = splitter.apply(original);
        vectorStore.add(splitted_documents);
    }


}
