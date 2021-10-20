package com.code;

import net.runelite.client.RuneLite;

import java.io.*;
import java.util.Scanner;

public class UserHandle {
    //sorry if this code is very messy its my first database "kindof" thing :)

    public static final File parent = new File(RuneLite.RUNELITE_DIR, "Bored");
    public static String datas;
    public static int data = 0;

    public static void handle() throws IOException {
        if(parent.createNewFile()) {
            System.out.println("file created succesfully");
        }
        Scanner scanner = new Scanner(parent);

        FileReader read = new FileReader(parent);
        BufferedReader reader = new BufferedReader(read);
        if(reader.read() == -1) {
            data++;
            datas = String.valueOf(data);
            System.out.println(datas);
            FileWriter fw = new FileWriter(parent);
            BufferedWriter buffer = new BufferedWriter(fw);
            buffer.write(datas+" ");
            buffer.close();
            BoredPanel.datatext.setText("Points: " + datas);
        }
        else {
            data = scanner.nextInt();
            data++;
            datas = String.valueOf(data);
            FileWriter fw = new FileWriter(parent);
            BufferedWriter buffer = new BufferedWriter(fw);
            buffer.write(datas+" ");
            buffer.close();
            BoredPanel.datatext.setText("Points: "+datas);

        }
        try {
            System.out.println(reader.read());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        reader.close();
    }
    public static void start() throws IOException {
        if(parent.createNewFile()) {
            System.out.println("first file created");
        }
        Scanner scanner = new Scanner(parent);

        FileReader read = new FileReader(parent);
        BufferedReader reader = new BufferedReader(read);

        if(reader.read() == -1) {
            datas = String.valueOf(data);
        }
        else {
            data = scanner.nextInt();
            datas = String.valueOf(data);
            BoredPanel.datatext.setText("Points: "+datas);

        }
    }
}


