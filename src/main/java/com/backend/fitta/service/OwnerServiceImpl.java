package com.backend.fitta.service;

import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    @Override
    public Long save(Owner owner) {
        return ownerRepository.save(owner).getId();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(()->new OwnerNotFoundException());
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner update(Long id, Owner owner) {
        Owner findOwner = findById(id);
        findOwner.changeOwnerInfo(owner);
        return findOwner;
    }

    @Override
    public void delete(Long id) {
        Owner findOwner = findById(id);
        ownerRepository.delete(findOwner);
    }
}
