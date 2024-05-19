package com.cosmetics.domain.goods.service;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.GoodsErrorManagement;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.dto.GoodsManagementResponse;
import com.cosmetics.domain.goods.entity.GoodsManagementEntity;
import com.cosmetics.domain.goods.repository.GoodsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional
    public GoodsManagementResponse findByGoodsNo(Long goodsNo) {
        GoodsManagementEntity goodsManagementEntity = goodsRepository.findByGoodsNo(goodsNo).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        //entity -> dto
        GoodsManagement goodsManagent = GoodsManagement.toDto(goodsManagementEntity);

        //dto -> response
        return GoodsManagementResponse.toResponseDto(goodsManagent);
    }

    @Transactional
    public GoodsManagementResponse save(GoodsManagement goodsManagement) {
        GoodsManagementEntity resultGoodsManagementEntity = goodsRepository.save(goodsManagement.toEntity());
        //entity -> dto
        GoodsManagement resultGoodsManagement = GoodsManagement.toDto(resultGoodsManagementEntity);

        //dto -> response
        GoodsManagementResponse resultGoodsManagementResponse = GoodsManagementResponse.toResponseDto(resultGoodsManagement);
        log.error("{}", resultGoodsManagement.getGoodsNo());
        log.error("{}", resultGoodsManagement.getItems().get(0).getItemNo());

        if (resultGoodsManagement.getGoodsNo() != null) {
            resultGoodsManagementResponse.updateSuccess("0000", "등록성공");
            log.error("{}", resultGoodsManagementResponse.getResultCode());
        } else {
            throw new CustomException(GoodsErrorManagement.GOODS_SAVE_ERROR);
        }

        return resultGoodsManagementResponse;
    }

    @Transactional
    public GoodsManagementResponse deleteByGoodsNo(Long goodsNo) {
        GoodsManagementEntity goodsManagementEntity = goodsRepository.findByGoodsNo(goodsNo).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        long deleteCnt = goodsRepository.deleteByGoodsNo(goodsNo);
        GoodsManagementResponse goodsManagementResponse = new GoodsManagementResponse();

        if (deleteCnt > 0) {
            goodsManagementResponse.updateSuccess("0000", "삭제성공");
        } else {
            throw new CustomException(GoodsErrorManagement.GOODS_SAVE_ERROR);
        }

        return goodsManagementResponse;
    }
}
