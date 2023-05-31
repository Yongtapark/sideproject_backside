package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.team.BasicStaffInfo;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateStaffRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Grade;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
@Transactional
class StaffApiServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    StaffApiService staffApiService;
    @Autowired
    GymApiService gymApiService;
    @Autowired
    TeamApiService teamApiService;

    @Test
    void saveAndFindById() {
        Long savedStaffId = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        BasicStaffInfo staffInfo = staffApiService.findById(savedStaffId);
        assertThat(staffInfo.getName()).isEqualTo("스태프1");
        assertThat(staffInfo.getBirthday()).isEqualTo(LocalDate.of(1999, 05, 04));
        assertThat(staffInfo.getGender()).isEqualTo( Gender.FEMALE);
        assertThat(staffInfo.getPhoneNumber()).isEqualTo("01012345678");
        assertThat(staffInfo.getAddress()).isEqualTo("서울");
        assertThat(staffInfo.getGrade()).isEqualTo(Grade.TRAINER);
    }

    @Test
    void findAll() {
        staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        staffApiService.save(new SaveStaffRequest("스태프2", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        staffApiService.save(new SaveStaffRequest("스태프3", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        Result<List<BasicStaffInfo>> all = staffApiService.findAll();
        assertThat(all.getData().size()).isEqualTo(3);
        assertThat(all.getData().get(0).getName()).isEqualTo("스태프1");
        assertThat(all.getData().get(1).getName()).isEqualTo("스태프2");
        assertThat(all.getData().get(2).getName()).isEqualTo("스태프3");
    }

    @Test
    void update() {
        Long savedStaffId = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        Staff staff = staffRepository.findById(savedStaffId).orElseThrow();
        staffApiService.update(savedStaffId, new UpdateStaffRequest("이름 바뀐 스태프", LocalDate.of(1999, 05, 04), "01012345678", "서울", Grade.TRAINER));
        assertThat(staff.getName()).isEqualTo("이름 바뀐 스태프");
    }

    @Test
    void delete() {
        Long savedStaffId = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        staffApiService.delete(savedStaffId);
        assertThatThrownBy(() -> staffApiService.findById(savedStaffId))
                .isInstanceOf(StaffNotFoundException.class);
    }

    @Test
    void saveTeamStaff() {
        Long savedStaffId = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        Long savedTeamId = teamApiService.save(new SaveTeamRequest("팀1"));
        staffApiService.saveTeamStaff(savedStaffId, savedTeamId);
        Staff staff = staffRepository.findById(savedStaffId).orElseThrow();
        assertThat(staff.getTeam().getName()).isEqualTo("팀1");
    }

    @Test
    void saveGymStaff() {
        Long savedStaffId = staffApiService.save(new SaveStaffRequest("스태프1", LocalDate.of(1999, 05, 04), Gender.FEMALE, "01012345678", "서울", Grade.TRAINER, null, null));
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012345678", "안산", GenderDivision.UNISEX));
        staffApiService.saveGymStaff(savedStaffId, savedGymId);
        Staff staff = staffRepository.findById(savedStaffId).orElseThrow();
        assertThat(staff.getGym().getName()).isEqualTo("헬스장1");
        assertThat(staff.getGym().getPhoneNumber()).isEqualTo("01012345678");
        assertThat(staff.getGym().getAddress()).isEqualTo("안산");
        assertThat(staff.getGym().getGenderDivision()).isEqualTo(GenderDivision.UNISEX);
    }
}