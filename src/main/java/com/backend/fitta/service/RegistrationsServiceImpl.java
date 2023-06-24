package com.backend.fitta.service;

import com.backend.fitta.entity.gym.Registrations;
import com.backend.fitta.exception.RegistrationsNotFoundException;
import com.backend.fitta.repository.registrations.RegistrationsRepository;
import com.backend.fitta.service.interfaces.RegistrationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class RegistrationsServiceImpl implements RegistrationsService {
    private final RegistrationsRepository registrationsRepository;
    @Override
    public Long save(Registrations registrations) {
        return registrationsRepository.save(registrations).getId();
    }

    @Override
    public Registrations findById(Long id) {
        return registrationsRepository.findById(id).orElseThrow(RegistrationsNotFoundException::new);
    }

    @Override
    public List<Registrations> findAll() {
        return registrationsRepository.findAll();
    }

    @Override
    public Long update(Long id, Registrations registrations) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Registrations findId = findById(id);
        registrationsRepository.delete(findId);
    }
}
