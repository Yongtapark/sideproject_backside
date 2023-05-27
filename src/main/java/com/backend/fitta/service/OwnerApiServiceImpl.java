package com.backend.fitta.service;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.AlreadyExistOwnerException;
import com.backend.fitta.exception.PWNotCorrespondException;
import com.backend.fitta.repository.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OwnerApiServiceImpl implements OwnerApiService {
    private final OwnerRepository ownerRepository;
    @Override
    public Long save(SignUpOwnerRequest request) {

//        Optional<Owner> findOwner = ownerRepository.findByEmail(request.getEmail());
//        if (!findOwner.isEmpty()) { //중복 체크
//            throw new AlreadyExistOwnerException();
//        }
//        if (!request.getPassword().equals(request.getPasswordConfirm())) {
//            throw new PWNotCorrespondException();
//        }
        log.info("request.getEmail()={}",request.getEmail());
        Owner owner = new Owner(request.getEmail(), request.getPassword(), request.getName(), request.getPhoneNumber(), request.getAddress(), request.getBusinessRegistrationNumber());
        ownerRepository.save(owner);
        return owner.getId();

    }

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
    public BasicOwnerInfo update(Long id, BasicOwnerInfo updatedOwnerInfo) {
//        Owner updateOwner = new Owner(
//                updatedOwnerInfo.getName(),
//                updatedOwnerInfo.getPhoneNumber(),
//                updatedOwnerInfo.getAddress(),
//                updatedOwnerInfo.getBusinessRegistrationNumber()
//        );
//        Owner update = ownerRepository.update(id, updateOwner);
//        return new BasicOwnerInfo(update);
        return null;
    }

}
