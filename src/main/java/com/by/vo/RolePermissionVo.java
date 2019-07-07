package com.by.vo;

import lombok.Data;

import java.util.List;
@Data
public class RolePermissionVo {

    private Integer roleId;

    private List <Integer> permissions;
}
