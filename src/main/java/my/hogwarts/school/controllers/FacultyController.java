package my.hogwarts.school.controllers;

import my.hogwarts.school.model.Faculty;
import my.hogwarts.school.model.Student;
import my.hogwarts.school.services.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.create(faculty));
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findById(@PathVariable long id) {
        Faculty findStudent = facultyService.findById(id);
        if (findStudent == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(findStudent);
    }

    @PutMapping
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty changeStudent = facultyService.update(faculty);
        if (changeStudent == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteById(@PathVariable long id) {
        facultyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/find_all")
    public ResponseEntity<Collection<Faculty>> findAll(@RequestParam(required = false) String string) {
        if (string != null && !string.isBlank()) {
            return ResponseEntity.ok(facultyService.findByNameOrColor(string));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/students/{idFaculty}")
    public ResponseEntity<Collection<Student>> findStudentsByFacultyId(@PathVariable Long idFaculty) {
        Faculty findStudent = facultyService.findById(idFaculty);
        if (findStudent == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(facultyService.findStudentsByFacultyId(idFaculty));
    }


}
