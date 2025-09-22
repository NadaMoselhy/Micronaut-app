package com.example.controller;

import com.example.model.dto.CreateItemDto;
import com.example.model.dto.ItemResponseDto;
import com.example.model.dto.UpdateItemDto;
import com.example.model.entity.Item;
import com.example.model.mapper.ItemMapper;
import com.example.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

@Controller("/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ItemMapper itemMapper;

    public ProductController(ProductService productService, ItemMapper itemMapper) {
        this.productService = productService;
        this.itemMapper = itemMapper;
    }

    @Get("/{id}")
    public HttpResponse<ItemResponseDto> getProductById(@PathVariable Long id){
        Item item = productService.getItem(id);
        return HttpResponse.ok(itemMapper.toDto(item));
    }

    @Post()
    public HttpResponse<ItemResponseDto> createProduct(@Body CreateItemDto createItemDto){
        Item item = productService.create(createItemDto);
        return HttpResponse.created(itemMapper.toDto(item));
    }

    @Put("/{id}")
    public HttpResponse<ItemResponseDto> updateProduct(@PathVariable Long id,@Body UpdateItemDto updateItemDto){
        Item item = productService.updateItem(updateItemDto);
        return HttpResponse.ok(itemMapper.toDto(item));
    }

    @Delete("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteItem(id);
    }




}
