package com.example.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
        private Long id;
        public ProductNotFoundException(Long id) {
            super("Product with this name is not found : " + id);
            this.id = id;

        }
}
