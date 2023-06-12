package com.backend.fitta.entity.utils;

import com.backend.fitta.entity.enums.Role;

public interface Users {
    String getEmail();
    String getPassword();
    Role getRole();

}
