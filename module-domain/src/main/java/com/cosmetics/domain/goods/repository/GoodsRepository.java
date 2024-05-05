package com.cosmetics.domain.goods.repository;

import com.cosmetics.domain.goods.dto.GoodsManagement;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */
@Repository
public class GoodsRepository {
    private static Map<String, GoodsManagement> goodsMap = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong();

    @Getter
    private static final GoodsRepository instance = new GoodsRepository();

    public GoodsManagement save(GoodsManagement goodsManagement){
        String goodsNo = "24050110000" + sequence.incrementAndGet();
        goodsManagement.createGoodsNo(goodsNo);
        goodsMap.put(goodsNo, goodsManagement);
        return goodsManagement;
    }

    public GoodsManagement deleteGoods(String goodsNo) {
        GoodsManagement removeGoods = goodsMap.get(goodsNo);

        goodsMap.remove(goodsNo);

        return removeGoods;
    }

    public GoodsManagement findGoods(String goodsNo){
        return goodsMap.get(goodsNo);
    };

}
