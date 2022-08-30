package com.ironhack.crm.view;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CRMView {
    private String text;

    public CRMView() {
    }

    public void showMenu(String menu) throws IOException {
        setText(readMenu(menu));
        printMenu();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String readMenu(String menu) throws IOException {
        //return StreamUtils.copyToString( new ClassPathResource("menu/"+menu+".txt").getInputStream(), Charset.defaultCharset());
        return Files.readString(Paths.get("src/main/resources/menu/"+menu+".txt"));
    }

    private void printMenu() {
        System.out.println(text);
    }


}
