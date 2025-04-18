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


}
