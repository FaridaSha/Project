package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //Метод позволяет показать все заказы
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
    //Данный метод позволяет получить заказ по id
    public Order getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }
    // Данный метод позволяет удалить заказ по id
    @Transactional
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    //Данный метод позволяет обновить информацию о заказе
    @Transactional
    public void updateOrder(int id, Order order) {
        order.setId(id);
        orderRepository.save(order);
    }
    @Transactional
    public Order findByLastFourCharacters(String str) {
        Optional<Order> orders = orderRepository.findByLastFourCharacters(str);
        return orders.orElse(null);
    }
}