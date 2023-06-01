package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.schedule.BasicScheduleInfo;
import com.backend.fitta.dto.schedule.SaveScheduleRequest;
import com.backend.fitta.dto.schedule.UpdateScheduleRequest;
import com.backend.fitta.entity.gym.Schedule;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.exception.ScheduleNotFoundException;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.repository.schedule.ScheduleRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.service.apiService.interfaces.ScheduleApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleApiServiceImpl implements ScheduleApiService {
    private final ScheduleRepository scheduleRepository;
    private final StaffRepository staffRepository;
    @Override
    public Long save(SaveScheduleRequest request) {
        Staff staff = staffRepository.findById(request.getStaffId()).orElseThrow(() -> new StaffNotFoundException());
        Schedule schedule = new Schedule(request.getStartTime(), request.getEndTime(), request.getDate(), staff);
        return scheduleRepository.save(schedule).getId();
    }

    @Override
    public BasicScheduleInfo findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        return new BasicScheduleInfo(schedule);
    }

    @Override
    public Result<List<BasicScheduleInfo>> findAll() {
        return null;
    }

    @Override
    public Long updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule findSchedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException());
        findSchedule.changeScheduleInfo(request.getStartTime(), request.getEndTime(), request.getDate(), request.getStaff());
        return findSchedule.getId();
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException());
        scheduleRepository.delete(schedule);
    }
}
