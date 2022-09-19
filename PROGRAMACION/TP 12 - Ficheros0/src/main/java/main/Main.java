package main;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {


    public static void main(String[] args) {
        Properties miProp = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config/miProperty.properties")) {
            miProp.load(fileInputStream);
            System.out.println(miProp.getProperty("color"));
        } catch (FileNotFoundException e) {
            System.out.println("Tu archivo no existe, escribe bien boludo");
        } catch (IOException e) {
            System.out.println("Excepcion rara");
        }

    }
}
