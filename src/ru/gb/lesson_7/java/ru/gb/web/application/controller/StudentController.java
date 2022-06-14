package ru.gb.lesson_7.main.java.ru.gb.web.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.guides.springboot2.springboot2webappjsp.repositories.UserRepository;

@Controller
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/student")
    public String home(Model model) {
        model.addAttribute("student", studentRepository.findAll());
        return "index";
    }
}