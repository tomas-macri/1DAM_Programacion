package ui;

import lombok.SneakyThrows;

import java.util.Properties;

public class Main {

    public static final String CONFIG_TESTING = "testing";

    public static void main(String[] args) {
        Main m = new Main();
        m.testProperties();
    }

    @SneakyThrows
    public void testProperties() {
        Properties pro = new Properties();
        pro.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        System.out.println(pro.get(CONFIG_TESTING));

        pro.entrySet().stream().map(objectObjectEntry -> objectObjectEntry.getKey() + " "
                + objectObjectEntry.getValue()).forEach(System.out::println);
    }

}