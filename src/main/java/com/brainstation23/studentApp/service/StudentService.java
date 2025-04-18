package com.brainstation23.studentApp.service;

import com.brainstation23.studentApp.dao.StudentDAO;
import com.brainstation23.studentApp.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
