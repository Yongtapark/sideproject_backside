package com.backend.fitta.dto.owner;

import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerProfileInfo {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String businessRegistrationNumber;
    private Role role;

    public OwnerProfileInfo(Owner owner) {
        this.id = owner.getId();
        this.email = owner.getEmail();
        this.name = owner.getName();
        this.phoneNumber = owner.getPhoneNumber();
        this.address = owner.getAddress();
        this.businessRegistrationNumber = owner.getBusinessRegistrationNumber();
        this.role=owner.getRole();
    }
}
