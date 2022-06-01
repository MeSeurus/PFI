package ru.gb.lesson_5.service;

import ru.gb.lesson_5.dao.StudentDao;
import ru.gb.lesson_5.entity.StudentEntity;
import ru.gb.lesson_5.components.StudentInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;
    private static final Integer numberOfStudents = 1000;

    @Transactional
    public void createRandomStudents() {
        for (int i = 0; i < numberOfStudents; i++) {
            StudentEntity student = StudentInfo.getStudent();
            studentDao.add(student);
        }
    }

    public void add(StudentEntity student) {
        studentDao.add(student);
    }

    public void deleteById(Long id) {
        studentDao.deleteById(id);
    }

    public void delete(StudentEntity student) {
        studentDao.delete(student);
    }

    public void update(StudentEntity student) {
        studentDao.update(student);
    }

    public List<StudentEntity> findAll() {
        return studentDao.findAll();
    }

    public StudentEntity findById(Long id) {
        return studentDao.findById(id);
    }

    public StudentEntity findByName(String name) {
        return studentDao.findByName(name);
    }
}