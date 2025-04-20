package com.brainstation23.studentApp.dao;

import com.brainstation23.studentApp.config.DbConnector;
import com.brainstation23.studentApp.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String SELECT_ALL = "select * from students";
    private static final String INSERT  = "insert into students (name,age,email,department) VALUES (?,?,?,?)";
    private static final String UPDATE  = "UPDATE students SET name=?, age=?, email=?, department=? WHERE id=?";
    private static final String SELECT_BY_ID= "SELECT * FROM students WHERE id = ?";

    public List<Student> getAllStudents() throws SQLException {
        Connection connection = DbConnector.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Student> students = new ArrayList<>();
                while (resultSet.next()) {
                    students.add(new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("email"),
                            resultSet.getString("department")
                    ));
                }

                return students;
            }
        }
    }


    public void add(Student student) throws SQLException {
        Connection connection = DbConnector.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getDepartment());
            statement.executeUpdate();
        }
    }

    public void update(Student student) throws SQLException {
        Connection connection = DbConnector.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getDepartment());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
        }
    }

    public Student findById(int id) throws SQLException {
        Connection connection = DbConnector.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) return null;
                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("department")
                );
            }
        }
    }
}
