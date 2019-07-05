package com.by.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleVo {

    private Integer userId;

    private List <Integer> roles;
}
