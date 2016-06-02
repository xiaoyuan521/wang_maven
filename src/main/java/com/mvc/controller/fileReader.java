package com.mvc.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.springframework.stereotype.Controller;

@Controller
public class fileReader {
    public static void main(String[] args) {

        String inFile = "C:/Users/wang_changyuan/git/wangcyTest/WebContent/WEB-INF/views/upload/test.txt";
        String outFile = "C:/Users/wang_changyuan/git/wangcyTest/WebContent/WEB-INF/views/upload/out.txt";

        // 方法一
        // try {
        // // 1、在内存中打开要读取文件的字符流对象
        // Reader reader = new FileReader(inFile);
        //
        // int ch = reader.read();
        // StringBuffer stringBuffer = new StringBuffer();
        // // 循环读取，一次就读一个
        // while (ch != -1) {
        // stringBuffer.append((char)ch);
        // ch = reader.read();
        // }
        //
        // System.out.println(stringBuffer.toString());
        // // 3、关闭流
        // reader.close();
        // } catch (FileNotFoundException e) {
        // System.out.println("要读取的文件不存在：" + e.getMessage());
        // } catch (IOException e) {
        // System.out.println("文件读取错误：" + e.getMessage());
        // }

        // 方法二
        // 1、创建流对象

        try {
            Reader reader = new FileReader(inFile);
            // 构建高效流对象
            BufferedReader buffReader = new BufferedReader(reader);

            String line = buffReader.readLine();
            StringBuffer stringBuffer = new StringBuffer();

            while (line != null) {
                stringBuffer.append(line + "\r\n");
                line = buffReader.readLine();
            }
            System.out.println(stringBuffer.toString());
            // 3、关闭流
            buffReader.close();
            reader.close();

            Writer write = new FileWriter(outFile);
            BufferedWriter buffWriter = new BufferedWriter(write);
            buffWriter.write(stringBuffer.toString());

            buffWriter.close();
            write.close();
            System.out.println("写入成功！");
        } catch (FileNotFoundException e) {
            System.out.println("要读取的文件不存在：" + e.getMessage());
        } catch (IOException e) {
            System.out.println("文件读取错误：" + e.getMessage());
        }

    }

}
