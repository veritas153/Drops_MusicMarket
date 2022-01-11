package com.project.drops_musicmarket.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    private String cartOwner;

    private String cartProduct;

    private BigDecimal cartPrice;

    private String cartProductCode;

    private Date cartDate;

    @PrePersist
    void cartDate() {this.cartDate = new Date(); }

    @Builder
    public CartEntity(Long cartId, String cartOwner, String cartProduct,
                      BigDecimal cartPrice, String cartProductCode, Date cartDate){
        this.cartId = cartId;
        this.cartOwner = cartOwner;
        this.cartProduct = cartProduct;
        this.cartPrice = cartPrice;
        this.cartProductCode = cartProductCode;
        this.cartDate = cartDate;

    }
}
