package com.library.library.repositories;

import com.library.library.entities.Author;
import com.library.library.entities.PublishingHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

    @Query("SELECT author FROM Author author WHERE author.name = :name")
    public Author getByName(@Param("name") String name);
}
