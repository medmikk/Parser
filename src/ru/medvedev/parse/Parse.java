package ru.medvedev.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Parse {
    private Document document;
    private Elements elements;

    public void getWeb(String url){
        try {
            document = Jsoup.connect(url.replaceAll(";", "")).get();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getElsA(){
        elements = document.select("a");
    }

    public Element getURL(){
        Element e = elements.last();
        for (Element element: elements){
            if(element.attr("href").contains(".whl")){
                e = element;
            }
        }
        return e;
    }
}
