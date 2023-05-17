package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.gym.Team;

import java.util.List;

public interface TeamService {
    Long save(Team team);
    Team findById(Long id);
    List<Team> findAll();
    Long update(Long id, Team team);
    void delete(Long id);
}
