package ru.medvedev.parse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

public class Downloader {

    public void download(String url_, String filename) throws IOException {
        File f = new File(System.getProperty("user.dir") + "/NewFiles/" + filename);
        if(!f.exists()) {
            URL url = new URL(url_);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File(System.getProperty("user.dir") + "/NewFiles/" + filename).toPath());
        }

    }
}