package ru.gb.lesson_5.components;

import ru.gb.lesson_5.entity.StudentEntity;
import java.util.List;
import java.util.Random;

public class StudentInfo {

    private static final List<String> list;

    static {
        list = List.of("Andy","Ava","Brad","Betty","Chad","Cecilia",
                "Don","Deny","Elliot","Eva","Finn","Felicia","Geralt","Gina",
                "Hunter","Hailey","Ian","Inga","John","Josephine", "Ken", "Kylie");
    }

    private StudentInfo(){
    }

    public static StudentEntity getStudent(){
        Random random = new Random();
        return new StudentInfo(list.get(random.nextInt(list.size())), 1 + random.nextInt(7));
    }
}