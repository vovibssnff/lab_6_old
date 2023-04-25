package client.load;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import client.io.OutputEngine;
import client.io.Validator;
import client.data.LabWork;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Класс для парсинга объектов коллекции, записанных в файле .json
 */
public class Parser {
    public static ArrayList<LabWork> parse() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        ArrayList<LabWork> labWorkList = new ArrayList<>();
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.env");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String filename = prop.getProperty("FILE_NAME");
        try(JsonReader reader = new JsonReader(new FileReader(filename))) {
            reader.beginArray();
            while(reader.hasNext()) {
                JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
                LabWork lbwork = gson.fromJson(obj, LabWork.class);
                if (Validator.checkName(lbwork.getName())&&
                        Validator.checkId(lbwork.getId())&&
                        Validator.checkDifficulty(lbwork.getDifficulty())&&
                        Validator.checkMinimalPoint(lbwork.getMinimalPoint())&&
                        Validator.checkCreationDate(lbwork.getCreationDate().toString())&&
                        Validator.checkCoordinatesX(lbwork.getCoordinates().getX())&&
                        Validator.checkCoordinatesY(lbwork.getCoordinates().getY())&&
                        Validator.checkAuthorName(lbwork.getAuthor().getName())&&
                        Validator.checkPassportId(lbwork.getAuthor().getPassportID())&&
                        Validator.checkColor(lbwork.getAuthor().getEyeColor())&&
                        Validator.checkLocationX(lbwork.getAuthor().getLocation().getX())&&
                        Validator.checkLocationY(lbwork.getAuthor().getLocation().getY())) {
                    labWorkList.add(lbwork);
                    System.out.println(lbwork);
                } else {
                    System.out.println(OutputEngine.incorrectObject());
                }
            }
            reader.endArray();
            System.out.println(OutputEngine.successImport());
            return labWorkList;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(OutputEngine.importError());
        }
        return null;
    }

}