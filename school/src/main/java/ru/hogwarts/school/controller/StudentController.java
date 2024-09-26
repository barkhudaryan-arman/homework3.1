package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RequestMapping("student")
@RestController

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("{id}")
    public Student uptadeStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.uptadeStudent(id, student);
    }

    @DeleteMapping("{id}")
    public void setStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/filter/age")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }
}