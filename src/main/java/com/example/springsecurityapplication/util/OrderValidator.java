package com.example.springsecurityapplication.util;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/*
@Component
public class OrderValidator implements Validator {

    private final OrderService orderService;

    public OrderValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    // В данно методе указываем для какой модели предназначен данные валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

} */