package ru.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.api.modles.ViewModelsStudent;
import ru.api.services.PaginationService;
import ru.api.modles.Student;
import ru.api.repositories.StudentRepository;
import ru.api.services.StudentsService;

@RestController
@RequestMapping("api/")
public class Controller {

    private final StudentRepository studentRepository;
    private final StudentsService studentsService;
    private final PaginationService paginationService;

    @Autowired
    public Controller(StudentRepository studentRepositories, StudentsService studentsService, PaginationService paginationService) {
        this.studentRepository = studentRepositories;
        this.studentsService = studentsService;
        this.paginationService = paginationService;
    }

    @GetMapping("/getStudents")
    public ViewModelsStudent findAll(@RequestParam(value = "limit", defaultValue = "10") String limit,
                                     @RequestParam(value = "offsset", defaultValue = "0") String offset) {
        return studentsService.getStudents(limit,offset);
    }

    @PutMapping("/updateStudents")
    public Object updateStudents(@RequestParam("id") String idString,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "passport", defaultValue = "") String passport) {
        Integer id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            e.printStackTrace();
            return "Bad ID";
        }
        studentRepository.update(id, name, passport);
        return studentRepository.findById(id).get();
    }

    @PostMapping("/addStudent")
    public Object addStudent(@RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "passport", defaultValue = "") String passport) {
        Student student = new Student();
        student.setName(name);
        student.setPassport(passport);
        return studentRepository.save(student);
    }

    @DeleteMapping("/delStudent")
    public void delStudent(@RequestParam("id") String idString) {
        Integer id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Student st = studentRepository.findById(id).get();
        studentRepository.delete(st);
    }


}
