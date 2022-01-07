package com.project.drops_musicmarket.service;


import com.project.drops_musicmarket.DTO.SoundDto;

public interface CartService {
    void addProduct(SoundDto detectSound, String userId);
}
