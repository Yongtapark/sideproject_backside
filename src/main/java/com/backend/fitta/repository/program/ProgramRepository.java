package com.backend.fitta.repository.program;

import com.backend.fitta.entity.gym.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
    List<Program> findAllByGymId(Long gymId);
}
