package my.hogwarts.school.repositories;

import my.hogwarts.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Collection<Faculty> findByColor(String color);

    public Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
