package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.*;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GymApiServiceImpl implements GymApiService {
    private final GymRepository gymRepository;
    private final OwnerRepository ownerRepository;
    private final OwnerService ownerService;
    @Override
    public Long save(SaveGymRequest request) {
        Owner owner = ownerService.findById(request.getOwnerId());
        Gym gym = new Gym(request.getName(),owner, request.getPhoneNumber(), request.getAddress(), request.getGenderDivision(), request.getBusinessIdentificationNumber());
        return gymRepository.save(gym).getId();
    }

    @Override
    public BasicGymInfo findById(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        return new BasicGymInfo(gym);
    }

    @Override
    public Result<List<BasicGymInfo>> findAll() {
        List<Gym> all = gymRepository.findAll();
        List<BasicGymInfo> collect = all.stream()
                .map(G -> new BasicGymInfo(G))
                .collect(Collectors.toList());
        return new Result(collect);
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

    /*@Override
    public Page<BasicGymInfo> findAll(Pageable pageable) {
        *//*List<Gym> all = gymRepository.findAll().st*//*
        return null;
    }*/

}
