package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("SELECT * FROM student WHERE student.id = :student_id")
    LiveData<Student> getStudentById(int student_id);

    @Query("SELECT * FROM student s WHERE s.stop_id = :stop_id")
    LiveData<List<Student>> getStudentsByStopId(int stop_id);

    @Query("SELECT * FROM student s WHERE s.age = :age")
    LiveData<List<Student>> getStudentsByAge(int age);

    @Query("SELECT * FROM student s WHERE s.classroom = :classroom")
    LiveData<List<Student>> getStudentsByClassroom(String classroom);

    @Query("SELECT * FROM student s WHERE s.guardian_id = :guardian_id")
    LiveData<List<Student>> getStudentsByGuardianId(int guardian_id);

    @Query("SELECT * FROM student s WHERE s.rut = :rut")
    LiveData<List<Student>> getStudentByRut(String rut);
}
