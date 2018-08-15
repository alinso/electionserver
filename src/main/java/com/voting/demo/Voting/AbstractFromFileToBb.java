package com.voting.demo.Voting;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class AbstractFromFileToBb {


    private String filePath;



    public  List<String> readDataInString(){

        List<String> parties  =new ArrayList<>();
       // File file  = new File(filePath);
        File file = null;
        try {
            file = new ClassPathResource(filePath).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parties.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parties;
    }

    public  String getFilePath() {
        return filePath;
    }

    public   void setFilePath(String path) {
        filePath = path;
    }
}
