package com.cosmetics.domain.goods.service.impl;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.GoodsErrorManagement;
import com.cosmetics.domain.exception.error.MemberErrorManagement;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.repository.GoodsRepository;
import com.cosmetics.domain.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    // 상품은 싱글톤 작성
    GoodsRepository goodsRepository = GoodsRepository.getInstance();

    @Override
    public GoodsManagement findGoods(String goodsNo) {
        GoodsManagement goodsManagement = goodsRepository.findGoods(goodsNo);

        if (goodsManagement == null) {
            throw new IllegalArgumentException("did not find goods with no goods id");
        }

        return goodsManagement;
    }

    @Override
    public GoodsManagement save(GoodsManagement goodsManagement) {
        GoodsManagement resultGoodsMgmt = goodsRepository.save(goodsManagement);

        if (resultGoodsMgmt.getGoodsNo() != null && !resultGoodsMgmt.getGoodsNo().isEmpty()) {
            resultGoodsMgmt.updateSuccess("0000", "등록성공");
        } else {
            throw new CustomException(GoodsErrorManagement.GOODS_SAVE_ERROR);
        }

        return resultGoodsMgmt;
    }

    @Override
    public GoodsManagement deleteGoods(String goodsNo) {
        GoodsManagement removeGoods = goodsRepository.findGoods(goodsNo);

        if (removeGoods == null) {
            throw new IllegalArgumentException("삭제할 상품번호가 존재하지 않습니다.");
        }

        goodsRepository.deleteGoods(goodsNo);

        //확인필요 삭제가 잘되었는지 확인하는데 한번더 db를 찌르는 것은 비효율적인게 아닌가?
        GoodsManagement resultGoods = goodsRepository.findGoods(goodsNo);

        if (resultGoods != null) {
            throw new CustomException(GoodsErrorManagement.GOODS_SAVE_ERROR);
        }

        return removeGoods;
    }
}

