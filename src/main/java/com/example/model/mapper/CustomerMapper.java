package com.example.model.mapper;

import com.example.model.dto.CustomerResponseDto;
import com.example.model.dto.CustomerSignUpDto;
import com.example.model.dto.CustomerUpdateDto;
import com.example.model.entity.Customer;
import org.mapstruct.*;


@Mapper(componentModel = "jsr330")
public interface CustomerMapper {


    public  CustomerResponseDto toDto(Customer customer);

    public Customer fromSignupDto(CustomerSignUpDto customerSignUpDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromDto(CustomerUpdateDto customerUpdateDto,
                                      @MappingTarget Customer customer);

}
