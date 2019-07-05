package com.by.vo;

import com.by.model.Role;
import com.by.model.User;
import lombok.Data;

import java.util.List;

@Data
public class User1Vo extends User {

    private List<Role> roles;
}
