package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CartDto;
import com.project.drops_musicmarket.DTO.SoundDto;
import com.project.drops_musicmarket.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public void addProduct(SoundDto detectSound, String userId) {

//        String soundId = detectSound.getSoundId();
//        BigDecimal soundPrice = detectSound.getSoundPrice();
//        String soundName = detectSound.getSoundName();
//
//        CartDto cartDto = CartDto.builder()
//                .cartOwner(userId)
//                .cartProduct(soundName)
//                .cartPrice(soundPrice)
//                .cartProductCode(soundId)
//                .build();
//
//        cartRepository.save(cartDto.toEntity());





    }
}
