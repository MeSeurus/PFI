package ru.gb.lesson_5;

import ru.gb.lesson_5.entity.StudentEntity;
import ru.gb.lesson_5.dao.StudentDao;
import ru.gb.lesson_5.service.StudentService;
import ru.gb.lesson_5.components.MySessionFactory;
import org.hibernate.SessionFactory;

public class Application {
    public static void main(String[] args) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        StudentService studentService = new StudentService(new StudentDao(sessionFactory));

        studentService.createRandomStudents();
        System.out.println("Всего: " + studentService.findAll().size());

        StudentEntity newStudent = new StudentEntity("George", 3.0);
        studentService.add(newStudent);
        System.out.println("Добавить студента");
        System.out.println("Всего студентов: " + studentService.findAll().size());
        System.out.println("Новый стдент: " + studentService.findByName(newStudent.getName()));

        System.out.println("Исключить студента");
        newStudent = studentService.findByName(newStudent.getName());
        studentService.delete(newStudent);
        System.out.println("Всего студентов: " + studentService.findAll().size());

        StudentEntity student = studentService.findById(1L);
        System.out.println("Студент под номером 1: " + student);

        System.out.println("Обновить студента");
        student.setName("Jack");
        student.setMark(4.0);
        studentService.update(student);
        System.out.println("Обновить студента: " + studentService.findById(1L));

        System.out.println("Исключить студента номер 1");
        studentService.delete(student);
        System.out.println("Всего студентов: " + studentService.findAll().size());
        System.out.println("Студент номер 1: " + studentService.findById(1L));
        MySessionFactory.closeSessionFactory();
    }
}