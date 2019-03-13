package com.codecool.web.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFileNames {

    public static boolean getFiles(String filePath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

        for (File file : listOfFiles) {
            if (file.getName().equals("Tweets.xml")) {
                return true;
            }
        }
        return false;
    }
}
