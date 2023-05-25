package com.backend.fitta.service;

import com.backend.fitta.dto.gym.*;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.repository.GymRepository;
import com.backend.fitta.service.interfaces.GymApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class GymApiServiceImpl implements GymApiService {
    private final GymRepository gymRepository;
    @Override
    public Long save(SaveGymRequest request) {
        Gym gym = new Gym(request.getName(), null, request.getAddress(), request.getPhoneNumber(), request.getGenderDivision());
        return gymRepository.save(gym).getId();
    }

    @Override
    public FindGymByIdResponse findById(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        List<MemberGymResponse> memberList = gymRepository.searchGymMemberList(id);
        List<StaffGymResponse> staffList = gymRepository.searchGymStaffList(id);
        return new FindGymByIdResponse(gym.getOwner(), gym.getName(), gym.getPhoneNumber(), gym.getAddress(), gym.getGenderDivision(), memberList, staffList);
    }

    @Override
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @Override
    public Long update(Long id, UpdateGymRequest request) {
        Gym findGym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        findGym.changeGymInfo(request.getName(),request.getPhoneNumber(),request.getAddress(),request.getGenderDivision());
        return findGym.getId();
    }

    @Override
    public void delete(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        gymRepository.delete(gym);
    }
}
