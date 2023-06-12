package com.backend.fitta.dto.login;

import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String email;
    private String name;
    @Lob
    private String profileImage;
    private Role role;

    public UserProfile(String email, String name, String profileImage) {
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
    }

    public UserProfile(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.profileImage = member.getProfileImage();
        this.role = member.getRole();
    }

    public UserProfile(Owner owner) {
        this.id = owner.getId();
        this.email = owner.getEmail();
        this.name = owner.getName();
        this.profileImage = owner.getProfileImage();
        this.role = owner.getRole();
    }

    public UserProfile() {
    }
}


