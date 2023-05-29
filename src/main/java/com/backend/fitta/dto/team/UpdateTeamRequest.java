package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateTeamRequest {
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$",message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다 .")
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();

    public UpdateTeamRequest(String name) {
        this.name = name;
    }
}
