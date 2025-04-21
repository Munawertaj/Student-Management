package com.brainstation23.studentApp.controller;

import com.brainstation23.studentApp.model.Student;
import com.brainstation23.studentApp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) throws SQLException {
        service.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable int id,  Model model) throws SQLException {
        Student student = service.findById(id);
        model.addAttribute("student", student);
        return "update";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) throws SQLException {
        service.update(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) throws SQLException {
        model.addAttribute("student", service.findById(id));
        return "details";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) throws SQLException {
        service.delete(id);
        return "redirect:/students";
    }
}
