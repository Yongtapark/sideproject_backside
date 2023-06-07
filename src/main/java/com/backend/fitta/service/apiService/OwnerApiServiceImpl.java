package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.OwnerAllGymInfoResponse;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.AlreadyExistOwnerException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.exception.PWNotCorrespondException;
import com.backend.fitta.repository.owner.OwnerQueryRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
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
    private final OwnerQueryRepository ownerQueryRepository;
    @Override
    public Long save(SignUpOwnerRequest request) {

        Optional<Owner> findOwner = ownerRepository.findByEmail(request.getEmail());
        if (!findOwner.isEmpty()) { //중복 체크
            throw new AlreadyExistOwnerException();
        }
      /*  if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new PWNotCorrespondException();
        }*/
        log.info("request.getEmail()={}",request.getEmail());
        Owner owner = new Owner(request.getEmail(), request.getPassword(), request.getName(), request.getPhoneNumber(), request.getAddress(), request.getBusinessRegistrationNumber());
        ownerRepository.save(owner);
        return owner.getId();

    }

    @Override
    public BasicOwnerInfo findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        return new BasicOwnerInfo(owner);
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
    public Long update(Long id, UpdateOwnerRequest request) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        owner.changeOwnerInfo(owner);
        return id;
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        ownerRepository.delete(owner);
    }

    @Override
    public OwnerAllGymInfoResponse ownerAllGymInfo(Long OwnerId) {
        return null;
    }


}
