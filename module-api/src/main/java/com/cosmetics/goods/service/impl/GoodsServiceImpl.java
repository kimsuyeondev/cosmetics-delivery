package com.cosmetics.goods.service.impl;

import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.repository.GoodsRepository;
import com.cosmetics.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    // 상품은 싱글톤 작성
    GoodsRepository goodsRepository = GoodsRepository.getInstance();

    @Override
    public GoodsMgmt findGoods(String goodsNo) throws BadRequestException{
        GoodsMgmt goodsMgmt = goodsRepository.findGoods(goodsNo);

        if(goodsMgmt == null) {
            //디버깅으로 타는 것 확인
           throw new BadRequestException();
        }

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

