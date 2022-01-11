package com.project.drops_musicmarket.Repository;

import com.project.drops_musicmarket.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "DELETE FROM cart WHERE DATE(cartDate) + 15 <= DATE(now())", nativeQuery = true)
    List<CartEntity> deleteExpiredDate();

//    List<CartEntity> findAllByCartOwner(<List<String> cartOwner);

}
