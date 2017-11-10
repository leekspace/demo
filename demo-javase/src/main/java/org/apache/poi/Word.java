package org.apache.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class Word {

    public static HWPFDocument openFile(String sampleFileName) {
        try {
            InputStream is = new FileInputStream(new File(sampleFileName));
            return new HWPFDocument(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        XWPFDocument document=new XWPFDocument();
        XWPFParagraph p1=document.createParagraph();//题目
        XWPFParagraph p2=document.createParagraph();//正文
        String title;
        List<String> txtList = new ArrayList<>();
        

        


        HtmlCleaner cleaner = new HtmlCleaner();  
        URL url = new URL("http://app.peopleapp.com/Api/600/DetailApi/shareArticle?type=0&article_id=784586"); 
        TagNode node = cleaner.clean(url.openStream());
        {
            Object[] tagNodes = node.evaluateXPath("//h1");
            TagNode textnode = (TagNode)tagNodes[0];
            title = textnode.getText().toString();
        }
        
        //正文
        {
            Object[]  txtNodes = node.evaluateXPath("//div[@class='article']/p");
            for (Object object : txtNodes) {
                TagNode textnode = (TagNode)object;
                String t1 = textnode.getText().toString();
                txtList.add("    "+t1);
            }
            
        }
        
        
        
        
        XWPFRun r1=p1.createRun();
        r1.setText(title);
        r1.addBreak();
        p1.setFontAlignment(2);
        
        
        
        for (String string : txtList) {
            XWPFRun r2=p2.createRun();
            r2.setText(string);
            r2.addBreak();
            r2.setFontSize(9);
        }
        
        
        
      
        
        FileOutputStream stream = new FileOutputStream(new File("D:\\test\\a.doc"));
        document.write(stream);
        
        
    }
}
