package com.by.vo;

import com.by.model.Permission;
import com.by.model.Role;
import lombok.Data;

import java.util.List;
@Data
public class RoleVo extends Role {

    private List<Permission> permissions;
}
