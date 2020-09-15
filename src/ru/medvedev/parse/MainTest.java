package ru.medvedev.parse;

import org.jsoup.nodes.Element;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(System.in);
        Parse parse = new Parse();
        Downloader downloader = new Downloader();
        Element downloading;

        String url1 = "https://pypi.org/simple/";
        String url2 = "";
        String fileName = "";
        String packageName = "";

        packageName = in.nextLine();

        parse.getWeb(url1 + packageName + "/");
        parse.getElsA();
        downloading = parse.getURL();
        url2 = downloading.attr("href");
        fileName = downloading.text();

        System.out.println();

        downloader.download(url2, fileName);

        System.out.println("Скачан " + packageName +"со ссылки " + url2);

    }


}
