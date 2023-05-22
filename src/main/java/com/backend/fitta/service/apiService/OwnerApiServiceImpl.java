package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerApiServiceImpl implements OwnerApiService {
    private final OwnerService ownerService;
    @Override
    public BasicOwnerInfo findById(Long id) {
        return new BasicOwnerInfo(ownerService.findById(id));
    }

    @Override
    public Result<BasicOwnerInfo> findAll() {
        List<Owner> all = ownerService.findAll();
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
        return ownerService.save(owner);
    }

    @Override
    public void delete(Long id) {

    }
}
