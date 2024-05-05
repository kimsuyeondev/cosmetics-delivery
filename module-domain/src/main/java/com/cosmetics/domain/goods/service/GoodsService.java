package com.cosmetics.domain.goods.service;

import com.cosmetics.domain.goods.dto.GoodsManagement;
import org.springframework.stereotype.Service;

@Service
public interface GoodsService {
   GoodsManagement findGoods(String goodsNo);

   GoodsManagement save(GoodsManagement goodsManagement);

   GoodsManagement deleteGoods(String goodsNo);
}
