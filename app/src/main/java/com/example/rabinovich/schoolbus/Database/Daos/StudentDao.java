package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Student;

public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("SELECT * FROM student WHERE student.id = :student_id")
    LiveData<Student> getStudentById(int student_id);
}
