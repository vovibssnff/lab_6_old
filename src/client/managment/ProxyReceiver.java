package client.managment;

import client.data.Color;
import client.data.Difficulty;
import client.data.LabWork;
import client.data.Person;
import client.io.InputEngine;
import client.io.Mode;
import client.io.OutputEngine;
import client.io.Validator;
import client_proxy.managment.PrgrmState;
;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProxyReceiver {
    public int iterations=0;
    public void help() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add : добавить новый элемент в коллекцию\n" +
                "update {id} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id {id} : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "head : вывести первый элемент коллекции\n" +
                "remove_lower {id} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 12 команд (без их аргументов)\n" +
                "cltmp {minimal_point}: вывести количество элементов, значение поля minimalPoint которых меньше заданного\n" +
                "print_unique_authors : вывести уникальные значения поля author всех элементов в коллекции\n" +
                "pfdmp : вывести значения поля minimalPoint всех элементов в порядке убывания");
    }
    public void info() {}
    public void soutСollection() {}

    /**
     * Скрипт для ввода и валидации значений полей новых элементов основной коллекции
     * @param type - режим ввода
     * @param ID - id, используется в команде update
     * @return elem - новый элемент коллекции
     */
    private LabWork setElemScript(int type, long ID) {
        Scanner sc = PrgrmState.getScanner();

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

        if (type==0) {
            elem.setId();
        }
        if (type==1) {
            elem.setId(ID);
        }
        System.out.println(OutputEngine.insertName());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                name = sc.nextLine().trim();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkName(name));
        elem.setName(name);

        System.out.println(OutputEngine.insertCoordinatesX());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    coordinatesX = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkCoordinatesX(coordinatesX)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectCoordinatesX());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectCoordinatesX());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println(OutputEngine.insertCoordinatesY());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextLong()) {
                    coordinatesY = sc.nextLong();
                    sc.nextLine();
                    if (Validator.checkCoordinatesY(coordinatesY)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectCoordinatesY());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectCoordinatesY());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        elem.setCoordinates(coordinatesX, coordinatesY);

        elem.setCreationDate();

        System.out.println(OutputEngine.insertMinimalPoint());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    minimalPoint = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkMinimalPoint(minimalPoint)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectMinimalPoint());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectMinimalPoint());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        elem.setMinimalPoint(minimalPoint);

        System.out.println(OutputEngine.insertDifficulty());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                difficultyStr = sc.nextLine().trim();
                difficulty = Difficulty.valueOf(difficultyStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!Validator.checkDifficulty(difficulty));
        elem.setDifficulty(difficulty);

        System.out.println(OutputEngine.insertAuthorName());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                authorName = sc.nextLine().trim();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkAuthorName(authorName));
        author.setName(authorName);

        author.setPassportID();

        System.out.println(OutputEngine.insertColor());
        do {
            try{
                System.out.print(OutputEngine.prompt());
                colorStr = sc.nextLine().trim();
                eyeColor = Color.valueOf(colorStr.toUpperCase());
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(!Validator.checkColor(eyeColor));
        author.setEyeColor(eyeColor);

        System.out.println(OutputEngine.insertLocationX());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextFloat()) {
                    locationX = sc.nextFloat();
                    sc.nextLine();
                    if (Validator.checkLocationX(locationX)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectLocationX());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectLocationX());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        author.getLocation().setX(locationX);

        System.out.println(OutputEngine.insertLocationY());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextDouble()) {
                    locationY = sc.nextDouble();
                    sc.nextLine();
                    if (Validator.checkLocationY(locationY)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectLocationY());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectLocationY());
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (true);
        author.getLocation().setY(locationY);

        System.out.println(OutputEngine.insertLocationZ());
        do {
            try {
                System.out.print(OutputEngine.prompt());
                if (sc.hasNextFloat()) {
                    locationZ = sc.nextFloat();
                    sc.nextLine();
                    if (Validator.checkLocationZ(locationZ)) {
                        break;
                    } else {
                        System.out.println(OutputEngine.incorrectLocationZ());
                    }
                } else {
                    System.out.println(OutputEngine.incorrectLocationZ());
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
    public void addElem() {}
    public void update() {}
    public void removeById() {}
    public void clear() {}
    public void save() {}
    public void executeScript(String filename) {
        iterations++;
        if (iterations>499) {
            System.out.println(OutputEngine.stackOverflowError());
            return;
        }
        InputEngine.modeSwitcher(null, filename);
    }
    public void exit() {
        System.exit(0);
    }
    public void head() {}
    public void removeLower(long id) {}
    public void history() {}
    public void countLessThanMinimalPoint() {}
    public void printUniqueAuthor() {}
    public void printFieldDescendingMinimalPoint() {}
}
