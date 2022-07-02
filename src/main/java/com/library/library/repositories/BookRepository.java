package com.library.library.repositories;

import com.library.library.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
}
