package com.backend.fitta.repository.schedule;

import com.backend.fitta.entity.gym.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
