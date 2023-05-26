package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.service.interfaces.OwnerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerApiServiceImpl implements com.backend.fitta.service.apiService.interfaces.OwnerApiService {
    private final OwnerApiService ownerApiService;
    @Override
    public BasicOwnerInfo findById(Long id) {
        return new BasicOwnerInfo(ownerApiService.findById(id));
    }

    @Override
    public Result<List<BasicOwnerInfo>> findAll() {
        List<Owner> all = ownerApiService.findAll();
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
        return ownerApiService.save(owner);
    }

    @Override
    public BasicOwnerInfo update(Long id, BasicOwnerInfo updatedOwnerInfo) {
        Owner updateOwner = new Owner(
                updatedOwnerInfo.getName(),
                updatedOwnerInfo.getPhoneNumber(),
                updatedOwnerInfo.getAddress(),
                updatedOwnerInfo.getBusinessRegistrationNumber()
        );
        Owner update = ownerApiService.update(id, updateOwner);
        return new BasicOwnerInfo(update);
    }

}
