package com.cosmetics.goods.service;

import com.cosmetics.goods.GoodsMgmt;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GoodsService {
   GoodsMgmt findGoods(String goodsNo) throws BadRequestException;

   void save(GoodsMgmt goodsMgmt);

   void deleteGoods(String goodsNo, Map<String, String> resultMap);
}
