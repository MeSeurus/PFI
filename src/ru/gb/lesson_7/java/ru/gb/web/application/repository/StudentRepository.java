package ru.gb.lesson_7.main.java.ru.gb.web.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.lesson_7.main.java.ru.gb.web.application.entity.student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}