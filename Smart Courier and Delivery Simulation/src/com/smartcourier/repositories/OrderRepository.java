package com.smartcourier.repositories;

import com.smartcourier.enums.OrderStatus;
import com.smartcourier.models.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OrderRepository {
    private final Map<Integer, Order> orders = new ConcurrentHashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }


    public List<Order> findByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(order -> order.getStatus() == status)
                .collect(Collectors.toList());
    }
}