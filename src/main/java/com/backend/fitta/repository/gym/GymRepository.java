package com.backend.fitta.repository.gym;

import com.backend.fitta.entity.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GymRepository extends JpaRepository<Gym,Long> {
}
