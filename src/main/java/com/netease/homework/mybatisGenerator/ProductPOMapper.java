package com.netease.homework.mybatisGenerator;

import com.netease.homework.mybatisGenerator.ProductPO;
import java.util.List;

public interface ProductPOMapper {
    int insert(ProductPO record);

    List<ProductPO> selectAll();
}