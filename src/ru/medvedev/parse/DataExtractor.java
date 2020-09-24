package ru.medvedev.parse;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.*;

public class DataExtractor {


    public String getData(String pass) throws Exception{

        ZipInputStream zin = new ZipInputStream(new FileInputStream(pass));
        ZipEntry entry;
        String zipName = "";
        InputStream inputStream;
        while((entry=zin.getNextEntry())!=null){
            if (entry.getName().contains("METADATA")) {
                zipName = entry.getName();
                break;
            }
            zin.closeEntry();
        }
        zin.close();

        ZipFile zipFile = new ZipFile(pass);
        ZipEntry zipEntry = zipFile.getEntry(zipName);
        InputStream iStream = zipFile.getInputStream(zipEntry);

        Scanner s = new Scanner(iStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        return result;
    }

    public ArrayList<String> getDeps(String data){
        ArrayList<String> deps = new ArrayList<>();
        if(!data.isEmpty())
            for (String line : data.split("\n")){
                if(line.split(" ").length > 0)
                    if(line.split(" ")[0].equals("Requires-Dist:") && !line.contains("extra ==")){
                       deps.add(line.split(" ")[1]);
                 }
            }
        return deps;
    }
}

