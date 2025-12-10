package my.hogwarts.school.services;

import my.hogwarts.school.model.Faculty;
import my.hogwarts.school.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty findById(long id) {
        if (facultyRepository.findById(id).isEmpty()) return null;
        return facultyRepository.getReferenceById(id);
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty update(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()).isEmpty()) return null;
        return facultyRepository.save(faculty);
    }

    public void deleteById(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}
