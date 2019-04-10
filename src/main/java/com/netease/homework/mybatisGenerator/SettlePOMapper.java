package com.netease.homework.mybatisGenerator;

import com.netease.homework.mybatisGenerator.SettlePO;
import java.util.List;

public interface SettlePOMapper {
    int insert(SettlePO record);

    List<SettlePO> selectAll();
}