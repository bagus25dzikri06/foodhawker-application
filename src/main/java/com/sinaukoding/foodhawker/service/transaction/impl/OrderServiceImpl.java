package com.sinaukoding.foodhawker.service.transaction.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.transaction.Order;
import com.sinaukoding.foodhawker.mapper.transaction.OrderMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.OrderFilterRecord;
import com.sinaukoding.foodhawker.model.request.OrderRequestRecord;
import com.sinaukoding.foodhawker.repository.transaction.OrderRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.transaction.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ValidatorService validatorService;
    private OrderMapper orderMapper;

    @Override
    public void add(OrderRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data order");
            log.debug("Request data order: {}", request);

            // validator mandatory
            validatorService.validator(request);

            if (request.totalAmount() < 0) {
                log.warn("Jumlah order tidak boleh kurang dari 0");
            }

            var order = orderMapper.requestToEntity(request);
            orderRepository.save(order);

            log.info("Order {} berhasil ditambahkan", request.id());
            log.trace("Tambah data order berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data order gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(OrderRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var orderExisting = orderRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
        var order = orderMapper.requestToEntity(request);
        order.setId(orderExisting.getId());
        orderRepository.save(order);
    }

    @Override
    public Page<SimpleMap> findAll(OrderFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Order> builder = new CustomBuilder<>();

        Page<Order> listOrder = orderRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listOrder.stream().map(order -> {
            SimpleMap data = new SimpleMap();
            data.put("restaurant", order.getRestaurant());
            data.put("totalAmount", order.getTotalAmount());
            data.put("orderStatus", order.getOrderStatus());
            data.put("orderItems", order.getOrderItems());
            data.put("status", order.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listOrder.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("restaurant", order.getRestaurant());
        data.put("totalAmount", order.getTotalAmount());
        data.put("orderStatus", order.getOrderStatus());
        data.put("orderItems", order.getOrderItems());
        data.put("status", order.getStatus());
        return data;
    }
}