package com.backend.fitta.service.gym;

import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerService {
    Long saveOwner(Owner owner);
    Owner selectOwner(Long id);
    List<Owner> selectAll();
    Long updateOwner(Long id, Owner owner);



}
