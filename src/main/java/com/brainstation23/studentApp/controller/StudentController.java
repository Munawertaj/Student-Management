package com.brainstation23.studentApp.controller;

import com.brainstation23.studentApp.model.Student;
import com.brainstation23.studentApp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService service = new StudentService();

    @GetMapping()
    public String showAllStudents(Model model) throws SQLException {
        List<Student> students = service.getAllStudents();
        model.addAttribute("students", students);
        return "home";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping
    public String addStudent(@ModelAttribute Student student) throws SQLException {
        service.addStudent(student);
        return "redirect:/students";
    }
}
