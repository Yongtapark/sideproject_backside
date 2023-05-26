package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Owner;

import java.util.List;

public interface OwnerApiService {
    Long save(Owner owner);
    Owner findById(Long id);
    List<Owner> findAll();
    Owner update(Long id, Owner owner);
    void delete(Long id);



}
