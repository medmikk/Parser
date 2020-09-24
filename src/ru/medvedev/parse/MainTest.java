package ru.medvedev.parse;

import org.jsoup.nodes.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainTest {

    public static Map<String, ArrayList<String>> dependency = new HashMap<String,ArrayList<String>>();
    public static ArrayList<String> downloadedPackages = new ArrayList<String>();

    public static boolean isNew(String val){
        for (String s: downloadedPackages){
            if (s.equals(val))
                return false;
        }
        return true;
    }

    public static void recurs(String packageName) throws Exception{
        Parse parse = new Parse();
        Downloader downloader = new Downloader();
        Element downloading;

        parse.getWeb("https://pypi.org/simple/" + packageName + "/");
        parse.getElsA();
        downloading = parse.getURL();

        downloader.download(downloading.attr("href"), downloading.text());
        System.out.println(downloading.text());
        downloadedPackages.add(packageName);

        DataExtractor extractor = new DataExtractor();
        if (downloading.text().contains(".whl")) {
            String data = extractor.getData(System.getProperty("user.dir") + "/NewFiles/" + downloading.text());

            ArrayList<String> currentDeps;
            currentDeps = extractor.getDeps(data);
            dependency.put(packageName, currentDeps);
            if(currentDeps.size() > 0) {
                for (String s : currentDeps)
                    if (isNew(s))
                        recurs(s);
            }
        }


    }

    public static void getGraphVizCode(){
        System.out.println("digraph G {");
        char dm = (char) 34;
        for (Map.Entry<String, ArrayList<String>> pair: dependency.entrySet()) {
            for (String s : pair.getValue()) {
                System.out.println(dm + pair.getKey() + dm + "->" + dm + s + dm);
            }
        }
        System.out.println("}");
    }

    public static void main(String[] args) throws Exception{
        File f = new File(System.getProperty("user.dir") + "/NewFiles");
        f.mkdir();

        Scanner in = new Scanner(System.in);
        recurs(in.nextLine());
        getGraphVizCode();

        f.delete();
    }
}
