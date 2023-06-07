package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.staff.BasicStaffInfo;
import com.backend.fitta.dto.staff.SaveStaffRequest;
import com.backend.fitta.dto.staff.UpdateStaffRequest;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.repository.team.TeamRepository;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class StaffApiServiceImpl implements StaffApiService {
    private final StaffRepository staffRepository;
    private final TeamRepository teamRepository;
    private final GymRepository gymRepository;

    Team team;

    @Override
    public Long save(SaveStaffRequest request) {
        if(request.getTeamId()!=null){
            team = teamRepository.findById(request.getTeamId()).orElseThrow(() -> new TeamNotFoundException());
        }
        Gym gym = gymRepository.findById(request.getGymId()).orElseThrow(() -> new GymNotFoundException());
        Staff staff = new Staff(request.getName(),request.getBirthday(),request.getGender(),request.getPhoneNumber(),request.getAddress(), gym, team);
        return staffRepository.save(staff).getId();
    }

    @Override
    public BasicStaffInfo findById(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        return new BasicStaffInfo(staff);
    }

    @Override
    public Result<List<BasicStaffInfo>> findAll() {
        List<Staff> all = staffRepository.findAll();
        List<BasicStaffInfo> collect = all.stream()
                .map(s -> new BasicStaffInfo(s))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Long update(Long id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        staff.changeStaffInfo(request.getName(),request.getBirthday(),request.getPhoneNumber(), request.getAddress());
        return staff.getId();
    }

    @Override
    public void delete(Long id) {
        Staff findStaff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        staffRepository.delete(findStaff);
    }

    @Override
    public void saveTeamStaff(long staffId, long teamId) {
        Staff findStaff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException());
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException());
        findStaff.changeTeam(team);
    }

    @Override
    public void saveGymStaff(long staffId, long gymId) {
        Staff findStaff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException());
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException());
        findStaff.changeGym(gym);
    }
}