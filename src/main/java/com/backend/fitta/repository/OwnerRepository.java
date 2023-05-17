package com.backend.fitta.repository;

import com.backend.fitta.entity.gym.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
