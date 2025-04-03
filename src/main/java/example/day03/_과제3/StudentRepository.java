package example.day03._과제3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends
        JpaRepository<StudentEntity, Integer> {
}
