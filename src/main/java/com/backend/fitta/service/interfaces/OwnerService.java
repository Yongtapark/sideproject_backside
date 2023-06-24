package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.owner.Owner;

import java.util.List;

public interface OwnerService {
    Long save(Owner owner);
    Owner findById(Long id);
    List<Owner> findAll();
    Owner update(Long id, Owner owner);
    void delete(Long id);

    Owner findByEmail(String email);



}
