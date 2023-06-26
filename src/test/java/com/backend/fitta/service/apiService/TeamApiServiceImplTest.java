/*
package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.team.BasicTeamInfo;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.repository.team.TeamRepository;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
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
class TeamApiServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    TeamApiService teamApiService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    GymRepository gymRepository;

    Staff savedStaff;

    @BeforeEach
    void init(){
        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Owner savedOwner = ownerRepository.save(owner);

        Gym gym = new Gym("powerGym", savedOwner, null,null,"12312321", "adddr", GenderDivision.UNISEX,"12312312");
        Gym savedGym = gymRepository.save(gym);

        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", savedGym, null);
        savedStaff = staffRepository.save(staff);
    }

    @Test
    void saveAndFindById() {
        Long savedTeamId = teamApiService.save(new SaveTeamRequest("팀1",savedStaff.getId()));
        BasicTeamInfo teamInfo = teamApiService.findById(savedTeamId);
        assertThat(teamInfo.getName()).isEqualTo("팀1");
    }

    @Test
    void findAll() {
        teamApiService.save(new SaveTeamRequest("팀1", savedStaff.getId()));
        teamApiService.save(new SaveTeamRequest("팀2",savedStaff.getId()));
        Result<List<BasicTeamInfo>> all = teamApiService.findAll();
        log.info("all={}",all);
        //assertThat(all.getData().size()).isEqualTo(2);
        //assertThat(all.getData().get(0).getName()).isEqualTo("팀1");
        //assertThat(all.getData().get(1).getName()).isEqualTo("팀2");
    }

    @Test
    void updateTeam() {
        Long savedTeamId = teamApiService.save(new SaveTeamRequest("팀1",savedStaff.getId()));
        Team team = teamRepository.findById(savedTeamId).orElseThrow();
        teamApiService.updateTeam(savedTeamId, new UpdateTeamRequest("이름바뀐 팀"));
        assertThat(team.getName()).isEqualTo("이름바뀐 팀");
    }

    @Test
    void deleteTeam() {
        Long savedTeamId = teamApiService.save(new SaveTeamRequest("팀1",savedStaff.getId()));
        teamApiService.deleteTeam(savedTeamId);
        assertThatThrownBy(() -> teamApiService.findById(savedTeamId))
                .isInstanceOf(TeamNotFoundException.class);
    }
}*/
