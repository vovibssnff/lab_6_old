package client_proxy.load;

import client_proxy.data.LabWork;
import client_proxy.managment.Collections;

import java.io.*;
import java.util.ArrayDeque;

/**
 * Класс для работы с .tmp файлом, который хранит несохраненные данные
 */
public class CollectionLoader {
    public static void save(File tmpFile) {
        try (FileOutputStream fos = new FileOutputStream(tmpFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Collections.getCollection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void load(File tmpFile) {
        try (FileInputStream fileInputStream = new FileInputStream(tmpFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) objectInputStream.readObject();
            Collections.setCollection(labWorks);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void loadScript(String filename) {

    }
}
