package com.library.library.controllers;

import com.library.library.dto.AuthorDto;
import com.library.library.entities.PublishingHouse;
import com.library.library.services.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/admin")
public class PublishingHouseController {

    @Autowired
    private PublishingHouseService publishingHouseService;

    @GetMapping(path="/publishing-house/ajax")
    @ResponseBody
    public Iterable<PublishingHouse> authors(@RequestParam Integer page){
        return publishingHouseService.getPublishingHouses(page);
    }

    @PostMapping(path="/publishing-house")
    public String create(@ModelAttribute("publishingHouseForm") AuthorDto authorDto){
        publishingHouseService.create(authorDto.getName());
        return "redirect:/admin/authors-and-publishing-houses/manage";
    }

    @GetMapping(path="/publishing-house/delete")
    public String delete(@RequestParam Integer id){
        publishingHouseService.delete(id);

        return "redirect:/admin/authors-and-publishing-houses/manage";
    }

}
