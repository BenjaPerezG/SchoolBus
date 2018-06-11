package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository mRepository;

    public StudentViewModel(Application application){
        super(application);
        mRepository = new StudentRepository(application);
    }
    public LiveData<List<Student>> getAllStudents(){return mRepository.getAllStudent();}
    public LiveData<Student> getStudentById(int student_id){return mRepository.getStudentById(student_id);}
    public LiveData<List<Student>> getStudentsByStopId(int stop_id){return mRepository.getStudentsByStopId(stop_id);}
    public LiveData<List<Student>> getStudentsByAge(int age){return mRepository.getStudentsByAge(age);}
    public LiveData<List<Student>> getStudentsByClassroom(String classroom){return mRepository.getStudentsByClassroom(classroom);}
    public LiveData<List<Student>> getStudentsByGuardianId(int guardian_id){return mRepository.getStudentsByGuardianId(guardian_id);}
    public LiveData<List<Student>> getStudentByRut(String rut){return mRepository.getStudentByRut(rut);}
    public void insert(Student student){mRepository.insert(student);}
}
