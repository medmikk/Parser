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
            document = Jsoup.connect(url).get();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getElsA(){
        elements = document.select("a");
    }

    public void printEls(){
        int i = 0;
        for (Element element: elements){
            System.out.println(element);
            i++;
            if (i > 5)
                break;
        }
    }

    public Element getURL(){
        System.out.println(elements.last().text());
        return elements.last();//.attr("href");
    }
}
