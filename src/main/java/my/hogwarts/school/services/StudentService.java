package my.hogwarts.school.services;

import my.hogwarts.school.model.Faculty;
import my.hogwarts.school.model.Student;
import my.hogwarts.school.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(long id) {
        if (studentRepository.findById(id).isEmpty()) return null;
        return studentRepository.getReferenceById(id);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        if (studentRepository.findById(student.getId()).isEmpty()) return null;
        return studentRepository.save(student);
    }

    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int startingAge, int endingAge) {
        return studentRepository.findByAgeBetween(startingAge, endingAge);
    }

    public Faculty findFacultyByStudentId(long idStudent) {
        return studentRepository.getReferenceById(idStudent).getOneFaculty();
    }
}
