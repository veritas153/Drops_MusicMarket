package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.CartEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartDto {

    private Long cartId;
    private String cartOwner;
    private String cartProduct;
    private BigDecimal cartPrice;
    private String cartProductCode;
    private Date cartDate;

    public CartEntity toEntity(){
        CartEntity cartEntity = CartEntity.builder()
                .cartOwner(cartOwner)
                .cartProduct(cartProduct)
                .cartProductCode(cartProductCode)
                .cartPrice(cartPrice)
                .cartDate(cartDate)
                .build();

        return cartEntity;
    }

    @Builder
    public CartDto(Long cartId, String cartOwner, String cartProduct,
                   BigDecimal cartPrice, String cartProductCode, Date cartDate){

        this.cartId = cartId;
        this.cartOwner = cartOwner;
        this.cartProduct = cartProduct;
        this.cartPrice = cartPrice;
        this.cartProductCode = cartProductCode;
        this.cartDate = cartDate;

    }


}
