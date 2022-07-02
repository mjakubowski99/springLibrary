package com.library.library.repositories;

import com.library.library.entities.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Integer> {

    ShoppingCart findByUserId(Integer UserId);

    Iterable<ShoppingCart> findAllByUserId(Integer UserId);
}
