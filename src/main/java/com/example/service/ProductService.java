package com.example.service;

import com.example.exception.InvalidCategoryException;
import com.example.model.dto.CreateItemDto;
import com.example.model.dto.UpdateItemDto;
import com.example.model.entity.Item;
import com.example.model.entity.Order;
import com.example.model.mapper.ItemMapper;
import com.example.repository.ProductRepositoryFacade;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {

    private final ProductRepositoryFacade productRepositoryFacade;
    private final ItemMapper itemMapper;

    public ProductService(ProductRepositoryFacade productRepositoryFacade, ItemMapper itemMapper) {
        this.productRepositoryFacade = productRepositoryFacade;
        this.itemMapper = itemMapper;
    }

    public Item getItem(Long id){
        return productRepositoryFacade.findById(id);
    }

    public Page<Item> getItems(Pageable pageable){
        return productRepositoryFacade.findAll(pageable);
    }

    public Page<Item> getItemByCategory(String category, Pageable pageable){
        if(isValidCategory(category)) {
            return productRepositoryFacade.findByCategory(category, pageable);
        }
        return Page.empty();

    }

    private boolean isValidCategory(String category){
        // i will change it to an enum and put validation on it later
        List<String> categoryList = List.of("electronics", "clothing", "books", "home");
        if(!categoryList.contains(category.toLowerCase())){
            throw new InvalidCategoryException(category);
        }

        return productRepositoryFacade.existsByCategory(category);

    }

    public Page<Item> getItemByName(String name , Pageable pageable){
        if(productRepositoryFacade.existsByName(name)){
            return productRepositoryFacade.findByNameContainingIgnoreCase(name,pageable);
        }
        return Page.empty();
    }

    public Page<Item> getItemsBetweenPrices(double minPrice, double maxPrice, Pageable pageable){
        // add validation of prices
        return productRepositoryFacade.findByPriceBetween(minPrice,maxPrice,pageable);
    }

    public Item create(CreateItemDto createItemDto){
        // do validation for category , price and stock
        Item item = itemMapper.fromCreateItemDto(createItemDto);
        return productRepositoryFacade.save(item);
    }

    public void deleteItem(Long id){
        productRepositoryFacade.deleteById(id);
    }

    public Item updateItem(UpdateItemDto updateItemDto){
        // also need to validate category , price and stock
        return itemMapper.fromUpdateItemDto(updateItemDto);
    }
}
