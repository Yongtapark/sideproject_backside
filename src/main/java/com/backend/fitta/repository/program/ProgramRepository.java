package com.backend.fitta.repository.program;

import com.backend.fitta.entity.gym.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
}
