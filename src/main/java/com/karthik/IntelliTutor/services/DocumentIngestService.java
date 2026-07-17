package com.karthik.IntelliTutor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentIngestService {

    private final VectorStore vectorStore;

    public int readPdf(Resource pdf){
        PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(pdf);
        List<Document> pages=pagePdfDocumentReader.get();
        TextSplitter splitter= TokenTextSplitter.builder().build();
        List<Document> chunks=splitter.apply(pages);
        vectorStore.add(chunks);
        return chunks.size();
    }
}
