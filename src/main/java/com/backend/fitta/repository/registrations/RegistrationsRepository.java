package com.backend.fitta.repository.registrations;

import com.backend.fitta.entity.gym.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations,Long> {
}
