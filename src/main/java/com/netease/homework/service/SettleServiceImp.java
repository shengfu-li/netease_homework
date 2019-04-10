package com.netease.homework.service;

import com.netease.homework.repository.SettlePO;
import com.netease.homework.repository.SettlePOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleServiceImp implements SettleService {

    @Autowired
    SettlePOMapper settlePOMapper;
    @Autowired
    UserService userService;

    @Override
    public void insert(SettlePO settlePO) {
        settlePOMapper.insert(settlePO);
    }

    @Override
    public List<SettlePO> getByUserName(String username) {
        Integer userId=userService.getUserByName(username).get(0).getId();
        List<SettlePO> settlePOList=settlePOMapper.selectByUserId(userId);
        return settlePOList ;
    }
}
