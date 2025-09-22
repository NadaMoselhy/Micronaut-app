package com.example.model.mapper;

import com.example.model.dto.CreateItemDto;
import com.example.model.dto.ItemResponseDto;
import com.example.model.dto.UpdateItemDto;
import com.example.model.entity.Item;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jsr330")
public interface ItemMapper {
     Item fromCreateItemDto(CreateItemDto createItemDto);

     ItemResponseDto toDto(Item item);

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     Item fromUpdateItemDto(UpdateItemDto updateItemDto);
}
