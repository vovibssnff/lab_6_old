package client.managment;

import client.data.Color;
import client.data.Difficulty;
import client.data.LabWork;
import client.data.Person;
import client.io.Mode;
import client.io.Validator;
import client.io.InputEngine;
import client.io.OutputEngine;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, выполняющий команды
 */
public class UsrInputReceiver {
    private static Scanner sc;
    public int iterations = 0;

    public static void setScanner(Scanner scanner) {
        sc=scanner;
    }


    public static LabWork setElemScript(Long ID) {
        setScanner(ProgramState.getScanner());
        LabWork elem = new LabWork();
        String name = null;
        Double coordinatesX = null;
        Long coordinatesY = null;
        Double minimalPoint = null;
        String difficultyStr;
        Difficulty difficulty = null;
        String authorName = null;
        String colorStr;
        Color eyeColor=null;
        Float locationX = null;
        Double locationY = null;
        Float locationZ = null;
        Person author = new Person();

        switch (ProgramState.getMode()) {
            case ADD -> elem.setId();
            case UPDATE -> elem.setId(ID);
        }
        System.out.println(client.io.OutputEngine.insertName());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                name = sc.nextLine().trim();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkName(name));
        elem.setName(name);

        System.out.println(client.io.OutputEngine.insertCoordinatesX());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    coordinatesX = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkCoordinatesX(coordinatesX)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectCoordinatesX());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectCoordinatesX());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println(client.io.OutputEngine.insertCoordinatesY());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextLong()) {
                    coordinatesY = sc.nextLong();
                    sc.nextLine();
                    if (Validator.checkCoordinatesY(coordinatesY)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectCoordinatesY());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectCoordinatesY());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        elem.setCoordinates(coordinatesX, coordinatesY);

        elem.setCreationDate();

        System.out.println(client.io.OutputEngine.insertMinimalPoint());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    minimalPoint = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkMinimalPoint(minimalPoint)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectMinimalPoint());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectMinimalPoint());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        elem.setMinimalPoint(minimalPoint);

        System.out.println(client.io.OutputEngine.insertDifficulty());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                difficultyStr = sc.nextLine().trim();
                difficulty = Difficulty.valueOf(difficultyStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!Validator.checkDifficulty(difficulty));
        elem.setDifficulty(difficulty);

        System.out.println(client.io.OutputEngine.insertAuthorName());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                authorName = sc.nextLine().trim();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkAuthorName(authorName));
        author.setName(authorName);

        author.setPassportID();

        System.out.println(client.io.OutputEngine.insertColor());
        do {
            try{
                System.out.print(client.io.OutputEngine.prompt());
                colorStr = sc.nextLine().trim();
                eyeColor = Color.valueOf(colorStr.toUpperCase());
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkColor(eyeColor));
        author.setEyeColor(eyeColor);

        System.out.println(client.io.OutputEngine.insertLocationX());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextFloat()) {
                    locationX = sc.nextFloat();
                    sc.nextLine();
                    if (Validator.checkLocationX(locationX)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectLocationX());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectLocationX());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        author.getLocation().setX(locationX);

        System.out.println(client.io.OutputEngine.insertLocationY());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    locationY = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkLocationY(locationY)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectLocationY());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectLocationY());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        author.getLocation().setY(locationY);

        System.out.println(client.io.OutputEngine.insertLocationZ());
        do {
            try {
                System.out.print(client.io.OutputEngine.prompt());
                if (sc.hasNextFloat()) {
                    locationZ = sc.nextFloat();
                    sc.nextLine();
                    if (Validator.checkLocationZ(locationZ)) {
                        break;
                    } else {
                        System.out.println(client.io.OutputEngine.incorrectLocationZ());
                    }
                } else {
                    System.out.println(client.io.OutputEngine.incorrectLocationZ());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        author.getLocation().setZ(locationZ);
        elem.setAuthor(author);
        return elem;
    }
    public static void addElemValidation() {
        setElemScript(null);
        return true;
    }

    public void update(String arg, Scanner scanner, Mode mode) {
        CollectionsEngine.update(CollectionsEngine.searchInCollection(Long.parseLong(arg)), setElemScript(1, Long.parseLong(arg), scanner, mode));
    }

    public void exit() {
        System.exit(0);
    }
    public static boolean removeByIdValidator(String arg) {
        if (arg!=null) {
            try {
                Long.parseLong(arg);
                return true;
            } catch (RuntimeException e) {
                System.out.println(server.io.OutputEngine.incorrectLongArg());
                return false;
            }
        }
        return false;
    }
    public void executeScriptValidation(String filename) {
        ProgramState.setMode(Mode.FILE);
        iterations++;
        if (iterations>499) {
            System.out.println(OutputEngine.stackOverflowError());
            return;
        }
        InputEngine.launcher(null, null, filename);
    }
    public void removeLowerValidation(long id) {
        CollectionsEngine.removeLower(id);
    }
    public static boolean countLessThanMinimalPointValidation(double minimal_point) {
        return Validator.checkMinimalPoint(minimal_point);
    }




}
