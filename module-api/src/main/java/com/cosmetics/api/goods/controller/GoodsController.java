package com.cosmetics.api.goods.controller;

import com.cosmetics.domain.goods.dto.GoodsManagement;
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
    public GoodsManagement findGoods(@PathVariable String goodsNo) {
        return goodsService.findGoods(goodsNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsManagement registerGoods(@RequestBody @Valid GoodsManagement goodsManagement) {
        return goodsService.save(goodsManagement);
    }

    @DeleteMapping(value = "/{goodsNo}")
    public GoodsManagement deleteGoods(@PathVariable String goodsNo) {
        GoodsManagement goodsManagement = goodsService.deleteGoods(goodsNo);
        //확인필요 validator를 도입하고, 삭제를 void 에서 dto로 바꾸면서 한가지 난관에 봉착함. Response용 GoodsManagement를 따로 만들어야할까
        //삭제에서는 성공과 성공메세지와 goodsNo만을 내려주고싶다. 근데 다 null로 내려간다.
        return GoodsManagement.builder().resultCode("0000").resultMsg("삭제성공").goodsNo(goodsManagement.getGoodsNo()).build();
    }
}
