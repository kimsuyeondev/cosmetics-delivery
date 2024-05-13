package com.cosmetics.api.goods.controller;

import com.cosmetics.domain.goods.dto.GoodsManagementRequest;
import com.cosmetics.domain.goods.dto.GoodsManagementResponse;
import com.cosmetics.domain.goods.service.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/goods")
@RequiredArgsConstructor
@Slf4j
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping(value = "/{goodsNo}")
    public GoodsManagementResponse findGoods(@PathVariable Long goodsNo) {
        return goodsService.findByGoodsNo(goodsNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsManagementResponse registerGoods(@RequestBody @Valid GoodsManagementRequest goodsManagementRequest) {
        return goodsService.save(goodsManagementRequest.toServiceDto());
    }

    @DeleteMapping(value = "/{goodsNo}")
    public GoodsManagementResponse deleteGoods(@PathVariable Long goodsNo) {
        return goodsService.deleteByGoodsNo(goodsNo);
    }
}
