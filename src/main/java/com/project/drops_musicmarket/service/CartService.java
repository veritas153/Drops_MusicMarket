package com.project.drops_musicmarket.service;


import com.project.drops_musicmarket.DTO.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getCartList(String userId, Integer pageNum);

    Integer[] getPageList(Integer pageNum);

    void checkExpiredDate();

    void addProduct(CartDto putCart);

}
