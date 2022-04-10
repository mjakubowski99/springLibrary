package com.library.library.services;

import com.library.library.entities.PublishingHouse;
import org.springframework.beans.factory.annotation.Autowired;
import com.library.library.repositories.PublishingHouseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PublishingHouseService {

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    public Iterable<PublishingHouse> getPublishingHouses(Integer page){
        return publishingHouseRepository.findAll( PageRequest.of(page, 10) );
    }

    public void create(String name){
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName(name);
        publishingHouseRepository.save(publishingHouse);
    }

    public void delete(Integer id){
        publishingHouseRepository.deleteById(id);
    }
}
