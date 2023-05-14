package com.backend.fitta.repository;

import com.backend.fitta.entity.gym.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
