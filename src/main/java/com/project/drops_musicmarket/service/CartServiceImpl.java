package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CartDto;
import com.project.drops_musicmarket.Entity.CartEntity;
import com.project.drops_musicmarket.Repository.CartRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    private static final int BLOCK_PAGE_NUM_COUNT = 10; // Page numbers inside of blocks
    private static final int PAGE_POST_COUNT = 5; // Article numbers per page.

    @Override
    public List<CartDto> getCartList(String userId, Integer pageNum) {
        List<CartEntity> cartSearch = cartRepository.findAll();

        List<CartDto> cartList = new ArrayList<>();

        for (CartEntity cartInfo : cartSearch){
            CartDto cartDto = CartDto.builder()
                    .cartProduct(cartInfo.getCartProduct())
                    .cartPrice(cartInfo.getCartPrice())
                    .cartProductCode(cartInfo.getCartProductCode())
                    .cartDate(cartInfo.getCartDate())
                    .build();

            cartList.add(cartDto);
        }

        return cartList;
    }

    @Transactional
    public Long getCartCount(){
        return cartRepository.count();
    }

    @Override
    public Integer[] getPageList(Integer curPageNum) {
        Double postsTotalCount = Double.valueOf(this.getCartCount());

        Integer totalLastPageNum = (int)(Math.ceil(postsTotalCount/PAGE_POST_COUNT));

        Integer[] pageList = new Integer[totalLastPageNum];

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum <= 3) ? 1 : curPageNum-2;

        for(int val = curPageNum, i = 0 ; val <= blockLastPageNum ; val++ , i++){
            pageList[i] = val;
        }

        return pageList;
    }

    @Override
    @Scheduled(cron = "0 0 0/1 * * *")
    public void checkExpiredDate() {
        cartRepository.deleteExpiredDate();

    }


    @Override
    public void addProduct(CartDto putCart) {

        cartRepository.save(putCart.toEntity());

    }

}
