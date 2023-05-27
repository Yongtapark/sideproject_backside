package com.backend.fitta.service;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerApiServiceImpl implements com.backend.fitta.service.apiService.interfaces.OwnerApiService {
    private final OwnerRepository ownerRepository;
    @Override
    public BasicOwnerInfo findById(Long id) {
        return null;
//        return new BasicOwnerInfo(ownerRepository.findById(id));
    }

    @Override
    public Result<List<BasicOwnerInfo>> findAll() {
        List<Owner> all = ownerRepository.findAll();
        List<BasicOwnerInfo> collect = all.stream()
                .map(o -> new BasicOwnerInfo(o))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Long save(BasicOwnerInfo basicOwnerInfo) {
        Owner owner = new Owner(basicOwnerInfo.getName(),
                basicOwnerInfo.getPhoneNumber(),
                basicOwnerInfo.getAddress(),
                basicOwnerInfo.getBusinessRegistrationNumber());
//        return ownerRepository.save(owner);
        return null;
    }

    @Override
    public BasicOwnerInfo update(Long id, BasicOwnerInfo updatedOwnerInfo) {
        Owner updateOwner = new Owner(
                updatedOwnerInfo.getName(),
                updatedOwnerInfo.getPhoneNumber(),
                updatedOwnerInfo.getAddress(),
                updatedOwnerInfo.getBusinessRegistrationNumber()
        );
//        Owner update = ownerRepository.update(id, updateOwner);
//        return new BasicOwnerInfo(update);
        return null;
    }

}
