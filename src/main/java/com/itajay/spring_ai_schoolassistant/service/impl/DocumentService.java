package com.itajay.spring_ai_schoolassistant.service.impl;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    private final VectorStore vectorStore;

    public DocumentService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void ParagraphPdfEmbeddingDocument(Resource resource){

        List<Document> original = new ParagraphPdfDocumentReader(resource).get();
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(1000)       //token计数
                .withMinChunkSizeChars(400)
                .withKeepSeparator(true)
                .withPunctuationMarks(List.of('。', '？', '！','；'))
                .build();
        List<Document> splitted_documents = splitter.apply(original);
        List<List<Document>> embedding_list = partition(original, 10);
        //服务每次限制只能接入10条
       for(var documents:embedding_list){
           vectorStore.add(documents);
       }

    }
    public void TikaEmbeddingDocument(Resource resource){
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
        //List<Document> original = new TikaDocumentReader(resource,fromatter).get();
        List<Document> original = new ParagraphPdfDocumentReader(resource).get();
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(1000)       //token计数
                .withMinChunkSizeChars(400)
                .withKeepSeparator(true)
                .withPunctuationMarks(List.of('。', '？', '！','；'))
                .build();
        List<Document> splitted_documents = splitter.apply(original);
        List<List<Document>> embedding_list = partition(original, 10);
        //服务每次限制只能接入10条
        for(var documents:embedding_list){
            vectorStore.add(documents);
        }
    }
    public void PagePdfEmbeddingDocument(Resource resource){

        List<Document> original = new PagePdfDocumentReader(resource).get();
        List<List<Document>> embedding_list = partition(original, 10);
        //服务每次限制只能接入10条
        for(var documents:embedding_list){
            vectorStore.add(documents);
        }

    }

    public List<List<Document>> partition(List<Document> documents,int BatchSize){
        List<List<Document>>res=new ArrayList<>();
        if(documents==null||documents.isEmpty())return res;
        for(int i=0;i<documents.size();i+=BatchSize){
            res.add(documents.subList(i,Math.min(i+BatchSize,documents.size())));
        }
        return res;
    }

}
