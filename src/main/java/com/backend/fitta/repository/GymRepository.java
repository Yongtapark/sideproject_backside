package com.backend.fitta.repository;

import com.backend.fitta.entity.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym,Long>, MemberRepositoryCustom, StaffRepositoryCustom {
}
