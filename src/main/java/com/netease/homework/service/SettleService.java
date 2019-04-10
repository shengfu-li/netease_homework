package com.netease.homework.service;

import com.netease.homework.repository.SettlePO;

import java.util.List;

public interface SettleService {
    public void insert(SettlePO settlePO);
    public List<SettlePO> getByUserName(String username);
}
