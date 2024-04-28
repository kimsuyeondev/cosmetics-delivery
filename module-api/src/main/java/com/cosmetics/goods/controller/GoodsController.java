package com.cosmetics.goods.controller;

import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/v1/goods")
@RequiredArgsConstructor
@Slf4j
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping(value = "/{goodsNo}")
    @ResponseStatus(HttpStatus.OK)
    public GoodsMgmt findGoods(@PathVariable String goodsNo) {
        return goodsService.findGoods(goodsNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> registerGoods(@RequestBody GoodsMgmt goodsMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        goodsService.save(goodsMgmt);
        resultMap.put("resultCode", "0000");
        resultMap.put("goodsNo", goodsMgmt.getGoodsNo());
        log.info("resultMap= {}", resultMap);
        return resultMap;
    }

    @DeleteMapping(value = "/{goodsNo}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> deleteGoods(@PathVariable String goodsNo) {
        Map<String,String> resultMap = new HashMap<>();
        goodsService.deleteGoods(goodsNo, resultMap);
        log.info("resultMap= {}", resultMap);
        return resultMap;
    }
}
