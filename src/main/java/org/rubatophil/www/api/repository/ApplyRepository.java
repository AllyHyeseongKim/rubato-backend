package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Apply findByStudentId(String studentId);
}
