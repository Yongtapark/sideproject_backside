/*
package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.schedule.BasicScheduleInfo;
import com.backend.fitta.dto.schedule.SaveScheduleRequest;
import com.backend.fitta.dto.schedule.UpdateScheduleRequest;
import com.backend.fitta.dto.staff.SaveStaffRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.gym.Schedule;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.exception.ScheduleNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.schedule.ScheduleRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.service.apiService.interfaces.ScheduleApiService;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
@Transactional
class ScheduleApiServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    ScheduleApiService scheduleApiService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    StaffApiService staffApiService;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    GymRepository gymRepository;

    @Autowired
    StaffRepository staffRepository;

    Staff savedStaff;
    Gym savedGym;

    @BeforeEach
    void init(){
        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Owner savedOwner = ownerRepository.save(owner);

        Gym gym = new Gym("powerGym", savedOwner, "12312321", "adddr", GenderDivision.UNISEX,"12312312");
        savedGym = gymRepository.save(gym);

        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", savedGym, null);
        savedStaff = staffRepository.save(staff);
    }

    @Test
    void saveAndFindById() {
        Long savedScheduleId = scheduleApiService.save(new SaveScheduleRequest("09:00", "10:00", LocalDate.of(2023, 06, 03), savedStaff.getId()));
        BasicScheduleInfo scheduleInfo = scheduleApiService.findById(savedScheduleId);
        Assertions.assertThat(scheduleInfo.getStartTime()).isEqualTo("09:00");
        Assertions.assertThat(scheduleInfo.getEndTime()).isEqualTo("10:00");
        Assertions.assertThat(scheduleInfo.getDate()).isEqualTo(LocalDate.of(2023, 06, 03));
        Assertions.assertThat(scheduleInfo.getStaffName()).isEqualTo("staff");
    }


    @Test
    void findAll() {
        Long savedStaffId1 = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", null, savedGym.getId()));
        Long savedStaffId2 = staffApiService.save(new SaveStaffRequest("스태프2", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", null, savedGym.getId()));
        scheduleApiService.save(new SaveScheduleRequest("09:00", "10:00", LocalDate.of(2023, 06, 03), savedStaffId1));
        scheduleApiService.save(new SaveScheduleRequest("11:00", "13:00", LocalDate.of(2023, 06, 11), savedStaffId2));
        Result<List<BasicScheduleInfo>> all = scheduleApiService.findAll();
        //스케줄1
        assertThat(all.getData().size()).isEqualTo(2);
        assertThat(all.getData().get(0).getStartTime()).isEqualTo("09:00");
        assertThat(all.getData().get(0).getEndTime()).isEqualTo("10:00");
        assertThat(all.getData().get(0).getDate()).isEqualTo(LocalDate.of(2023, 06, 03));
        assertThat(all.getData().get(0).getStaffName()).isEqualTo("스태프1");
        //스케줄2
        assertThat(all.getData().get(1).getEndTime()).isEqualTo("13:00");
        assertThat(all.getData().get(1).getStartTime()).isEqualTo("11:00");
        assertThat(all.getData().get(1).getDate()).isEqualTo(LocalDate.of(2023, 06, 11));
        assertThat(all.getData().get(1).getStaffName()).isEqualTo("스태프2");
    }

    @Test
    void updateSchedule() {
        Long savedScheduleId = scheduleApiService.save(new SaveScheduleRequest("09:00", "10:00", LocalDate.of(2023, 06, 03), savedStaff.getId()));
        Schedule schedule = scheduleRepository.findById(savedScheduleId).orElseThrow();
        scheduleApiService.updateSchedule(savedScheduleId, new UpdateScheduleRequest("12:00", "14:00", LocalDate.of(2023, 06, 07), savedStaff.getId()));
        Assertions.assertThat(schedule.getStartTime()).isEqualTo("12:00");
        Assertions.assertThat(schedule.getEndTime()).isEqualTo("14:00");
        Assertions.assertThat(schedule.getDate()).isEqualTo(LocalDate.of(2023, 06, 07));
        Assertions.assertThat(schedule.getStaff().getName()).isEqualTo("staff");
    }

    @Test
    void deleteSchedule() {
        Long savedScheduleId = scheduleApiService.save(new SaveScheduleRequest("09:00", "10:00", LocalDate.of(2023, 06, 03), savedStaff.getId()));
        scheduleApiService.deleteSchedule(savedScheduleId);

        // 스케줄을 삭제하면
        assertThatThrownBy(() -> scheduleApiService.findById(savedScheduleId))
                .isInstanceOf(ScheduleNotFoundException.class);
    }
}*/
