package my.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import my.hogwarts.school.model.Student;
import my.hogwarts.school.services.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.create(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        Student findStudent = studentService.findById(id);
        if (findStudent == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(findStudent);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student changeStudent = studentService.update(student);
        if (changeStudent == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteById(@PathVariable long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam(required = false) Integer age) {
        if (age != null && age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
