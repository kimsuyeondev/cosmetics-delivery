package com.cosmetics.goods.service.impl;

import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.repository.GoodsRepository;
import com.cosmetics.goods.service.GoodsService;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    // 상품은 싱글톤 작성
    GoodsRepository goodsRepository = GoodsRepository.getInstance();

    @Override
    public GoodsMgmt findGoods(String goodsNo) {
        return goodsRepository.findGoods(goodsNo);
    }

    @Override
    public void save(GoodsMgmt goodsMgmt) {
        goodsRepository.save(goodsMgmt);
    }

    @Override
    public void deleteGoods(String goodsNo, Map<String, String> resultMap) {
        goodsRepository.deleteGoods(goodsNo, resultMap);
    }
}

