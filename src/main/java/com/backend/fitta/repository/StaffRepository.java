package com.backend.fitta.repository;

import com.backend.fitta.entity.gym.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long>,StaffRepositoryCustom {
}
