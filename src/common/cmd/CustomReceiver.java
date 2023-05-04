package common.cmd;

import server.managment.Mode;

import java.util.Scanner;

public interface CustomReceiver {
    void help();
    void clear();
    void update();
    void info();
    void soutCollection();
    void addElem();
    void removeById();
    void save();
    void executeScript();
    void exit();
    void head();
    void removeLower();
    void countLessThanMinimalPoint();
    void printUniqueAuthor();
    void printFieldDescendingMinimalPoint();
    void history();
}
