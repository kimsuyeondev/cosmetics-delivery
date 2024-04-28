package com.cosmetics.goods.repository;

import com.cosmetics.goods.GoodsMgmt;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */
@Repository
public class GoodsRepository {
    private static Map<String, GoodsMgmt> goodsMap = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong();

    private static final GoodsRepository instance = new GoodsRepository();

    public static GoodsRepository getInstance(){
        return instance;
    }

    public GoodsMgmt save(GoodsMgmt goodsMgmt){
        String goodsNo = "24050110000" + sequence.incrementAndGet();
        goodsMgmt.setGoodsNo(goodsNo);
        goodsMap.put(goodsNo, goodsMgmt);
        return goodsMgmt;
    }

    public GoodsMgmt findGoods(String goodsNo){
        return goodsMap.get(goodsNo);
    };

    public void deleteGoods(String goodsNo, Map<String, String> resultMap) {
        if(!goodsMap.containsKey(goodsNo)){
            resultMap.put("resultCode", "-0001");
            resultMap.put("retMsg", "존재하지 않는 상품입니다.");
            return;
        }

        goodsMap.remove(goodsNo);

        if(goodsMap.containsKey(goodsNo)){
            resultMap.put("resultCode", "-1111");
            resultMap.put("retMsg", "삭제실패");
        }else{
            resultMap.put("resultCode", "0000");
            resultMap.put("retMsg", "삭제성공");
        }
    }

}
