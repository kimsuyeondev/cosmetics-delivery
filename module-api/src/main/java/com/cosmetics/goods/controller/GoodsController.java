package com.cosmetics.goods.controller;

import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.repository.GoodsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/goods")
public class GoodsController {

    /** API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다. */
    GoodsRepository goodsRepository = GoodsRepository.getInstance();

    @GetMapping(value = "/{goodsNo}")
    public ResponseEntity<GoodsMgmt> findGoods(@PathVariable String goodsNo) {
        System.out.println(goodsNo);
        GoodsMgmt goodsMgmt = goodsRepository.findGoods(goodsNo);
        return new ResponseEntity<>(goodsMgmt, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> registerGoods(@RequestBody GoodsMgmt goodsMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("resultCode","0000");
        goodsRepository.save(goodsMgmt);
        resultMap.put("goodsNo", goodsMgmt.getGoodsNo());

        System.out.println(goodsMgmt.getGoodsNm());
        System.out.println(goodsMgmt.getItem().get(0).getItemNm());
        System.out.println(goodsMgmt.getItem().get(1).getItemNm());
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

}
