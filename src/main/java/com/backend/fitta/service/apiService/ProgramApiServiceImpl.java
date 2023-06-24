package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.program.ProgramInfo;
import com.backend.fitta.repository.program.ProgramRepository;
import com.backend.fitta.service.apiService.interfaces.ProgramApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramApiServiceImpl implements ProgramApiService {
    private final ProgramRepository programRepository;
    @Override
    public Long save(ProgramInfo programInfo) {
        return null;
    }

    @Override
    public ProgramInfo findById(Long id) {
        return null;
    }

    @Override
    public List<ProgramInfo> findAll() {
        return null;
    }

    @Override
    public List<ProgramInfo> findAllByGymId(Long gymId) {
        return programRepository.findAllByGymId(gymId).stream().map(o->new ProgramInfo(o)).collect(Collectors.toList());
    }

    @Override
    public Long update(Long id, ProgramInfo programInfo) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
