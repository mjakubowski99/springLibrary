package com.library.library.repositories;

import com.library.library.entities.PublishingHouse;
import com.library.library.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PublishingHouseRepository extends PagingAndSortingRepository<PublishingHouse, Integer> {

    @Query("SELECT publishingHouse FROM PublishingHouse publishingHouse WHERE publishingHouse.name = :name")
    public PublishingHouse getByName(@Param("name") String name);
}
