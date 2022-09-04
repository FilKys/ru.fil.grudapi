package ru.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.api.modles.Pagination;
import ru.api.modles.Student;
import ru.api.modles.ViewModelsStudent;
import ru.api.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentsService {

    StudentRepository studentRepository;
    PaginationService paginationService;
    ViewBuilderService viewBuilderService;

    @Autowired
    public StudentsService(StudentRepository studentRepositories, PaginationService paginationService, ViewBuilderService viewBuilderService) {
        this.studentRepository = studentRepositories;
        this.paginationService = paginationService;
        this.viewBuilderService = viewBuilderService;
    }


    public ViewModelsStudent getStudents(Pagination pagination) {
        if (pagination == null) pagination = paginationService.getDefault();
        List<Student> studentList = studentRepository.findAll(pagination.getLimit(), pagination.getOffset());
        pagination.setTotalCount(studentRepository.count());
        return viewBuilderService.getListStudents(studentList,pagination);
    }

    public ViewModelsStudent getStudents(String strLimit,String strOffset){
        return getStudents(paginationService.getPagination(strLimit,strOffset));
    }
}
