package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerService {
    Long save(Owner owner);
    Owner findById(Long id);
    List<Owner> findAll();
    Long update(Long id, Owner owner);
    void delete(Long id);



}
