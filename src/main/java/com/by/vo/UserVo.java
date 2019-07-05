package com.by.vo;

import lombok.Data;

import java.util.List;
@Data
public class UserVo {
    private List <Integer> ids;

    public List <Integer> getIds() {
        return ids;
    }

    public void setIds(List <Integer> ids) {
        this.ids = ids;
    }
}
