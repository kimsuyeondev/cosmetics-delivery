package com.cosmetics.api.goods.controller;

import com.cosmetics.domain.goods.dto.GoodsManagement;
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
        GoodsManagement goodsManagement = goodsService.findByGoodsNo(goodsNo);
        //dto -> responseDto
        GoodsManagementResponse responseGoodsManagement = GoodsManagementResponse.toResponseDto(goodsManagement);
        responseGoodsManagement.updateSuccess("0000", "조회성공");
        return responseGoodsManagement;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsManagementResponse registerGoods(@RequestBody @Valid GoodsManagementRequest goodsManagementRequest) {
        GoodsManagement goodsManagement = goodsService.save(goodsManagementRequest.toServiceDto());
        //dto -> responseDto
        GoodsManagementResponse responseGoodsManagement = GoodsManagementResponse.toResponseDto(goodsManagement);
        responseGoodsManagement.updateSuccess("0000", "등록성공");
        return responseGoodsManagement;
    }

    @DeleteMapping(value = "/{goodsNo}")
    public GoodsManagementResponse deleteGoods(@PathVariable Long goodsNo) {
        log.error("deleteGoods : {}", goodsNo);
        GoodsManagement goodsManagement = goodsService.deleteByGoodsNo(goodsNo);
        //dto -> responseDto
        GoodsManagementResponse responseGoodsManagement = GoodsManagementResponse.toResponseDto(goodsManagement);
        responseGoodsManagement.updateSuccess("0000", "삭제성공");
        return responseGoodsManagement;
    }

    //확인필요 같은 패턴이 반복될때 컨트롤러에 함수로 빼는게 나을까요
}
