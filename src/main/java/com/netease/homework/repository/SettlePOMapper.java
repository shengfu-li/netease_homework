package com.netease.homework.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlePOMapper {
    int insert(SettlePO record);

    List<SettlePO> selectAll();
    List<SettlePO> selectByUserId(Integer userId);
}