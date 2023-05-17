package com.backend.fitta.service;

import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.repository.GymRepository;
import com.backend.fitta.service.interfaces.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class GymServiceImpl implements GymService {
    private final GymRepository gymRepository;
    @Override
    public Long save(Gym gym) {
        return gymRepository.save(gym).getId();
    }

    @Override
    public Gym findById(Long id) {
        return gymRepository.findById(id).orElseThrow(()->new GymNotFoundException());
    }

    @Override
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @Override
    public Long update(Long id, Gym gym) {
        Gym findGym = findById(id);
        findGym.changeGymInfo(gym.getName(),gym.getPhoneNumber(),gym.getAddress(),gym.getGenderDivision());
        return findGym.getId();
    }

    @Override
    public void delete(Long id) {
        Gym findId = findById(id);
        gymRepository.delete(findId);
    }
}
