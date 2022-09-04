package ru.api.services;

import org.springframework.stereotype.Service;
import ru.api.modles.Pagination;
import ru.api.modles.Student;
import ru.api.modles.ViewModelsStudent;

import java.util.List;

@Service
public class ViewBuilderService {

    public ViewModelsStudent getListStudents(List<Student> studentList, Pagination pagination) {
        return ViewModelsStudent.builder().studentList(studentList).pagination(pagination).build();
    }
}
