package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.schedule.BasicScheduleInfo;
import com.backend.fitta.dto.schedule.SaveScheduleRequest;
import com.backend.fitta.dto.schedule.UpdateScheduleRequest;

import java.util.List;

public interface ScheduleApiService {
    Long save(SaveScheduleRequest request);
    BasicScheduleInfo findById(Long id);
    Result<List<BasicScheduleInfo>> findAll();
    Long updateSchedule(Long id, UpdateScheduleRequest request);
    void deleteSchedule(Long id);
}
