package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.gym.*;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.repository.GymRepository;
import com.backend.fitta.repository.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class GymApiServiceImpl implements GymApiService {
    private final GymRepository gymRepository;
    private final OwnerRepository ownerRepository;
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
        return new FindGymByIdResponse(gym.getName(), gym.getPhoneNumber(), gym.getAddress(), gym.getGenderDivision(), memberList, staffList);
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

    @Override
    public void saveOwnerGym(long gymId, long ownerId) {
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException());
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException());
        gym.changeOwner(owner);
    }
}
