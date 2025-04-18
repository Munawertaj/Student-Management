package com.brainstation23.studentApp.controller;

import com.brainstation23.studentApp.model.Student;
import com.brainstation23.studentApp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final StudentService service = new StudentService();

    @GetMapping
    public String home(Model model) throws SQLException {
        List<Student> students = service.getAllStudents();
        model.addAttribute("students", students);
        return "home";
    }
}
