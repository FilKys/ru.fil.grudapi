package ru.api.services;

import org.apache.tomcat.jni.Time;
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
        return viewBuilderService.getListStudents(studentList, pagination);
    }

    public ViewModelsStudent getStudents(String strLimit, String strOffset) {
        return getStudents(paginationService.getPagination(strLimit, strOffset));
    }

    public Student updateStudent(String idString, String name, String passport) {
        try {
            int id = Integer.parseInt(idString);
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setPassport(passport);
            return updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Student();
        }
    }

    public Student updateStudent(Student student) {
        if (student != null && student.getId() != null) {
            studentRepository.update(student.getId(),
                    student.getName(),
                    student.getPassport());
            return findById(student.getId());
        } else {
            return new Student();
        }
    }

    public Student saveStudent(Student student) {
        student.setId(null);
        if (student.getName() != null && student.getPassport() != null
                && !studentRepository.existsByPassport(student.getPassport())) {
            return studentRepository.save(student);
        } else return new Student();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    public Object saveStudent(String name, String passport) {
        Student student = new Student();
        student.setName(name);
        student.setPassport(passport);
        return saveStudent(student);
    }

    public void deleteStudent(Student student) {
        if (student != null && student.getId() != null) {
            studentRepository.deleteById(student.getId());
        }
    }

    public void deleteStudent(String idString) {
        try {
            int id = Integer.parseInt(idString);
            Student student = new Student();
            student.setId(id);
            deleteStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
