package com.sinaukoding.foodhawker.service.transaction.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.transaction.OrderItem;
import com.sinaukoding.foodhawker.mapper.transaction.OrderItemMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.OrderItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.OrderItemRequestRecord;
import com.sinaukoding.foodhawker.repository.transaction.OrderItemRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.transaction.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;
    private ValidatorService validatorService;
    private OrderItemMapper orderItemMapper;

    @Override
    public void add(OrderItemRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data item order");
            log.debug("Request data item order: {}", request);

            // validator mandatory
            validatorService.validator(request);

            if (request.quantity() < 0) {
                log.warn("Kuantitas tidak boleh kurang dari 0");
            }
            if (request.totalPrice() < 0) {
                log.warn("Total harga tidak boleh kurang dari 0");
            }

            var orderItem = orderItemMapper.requestToEntity(request);
            orderItemRepository.save(orderItem);

            log.info("Review {} berhasil ditambahkan", request.id());
            log.trace("Tambah data review berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data review gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(OrderItemRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var orderItemExisting = orderItemRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Order item not found")
        );
        var orderItem = orderItemMapper.requestToEntity(request);
        orderItem.setId(orderItemExisting.getId());
        orderItemRepository.save(orderItem);
    }

    @Override
    public Page<SimpleMap> findAll(OrderItemFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<OrderItem> builder = new CustomBuilder<>();

        Page<OrderItem> listOrderItem = orderItemRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listOrderItem.stream().map(orderItem -> {
            SimpleMap data = new SimpleMap();
            data.put("food", orderItem.getFood());
            data.put("quantity", orderItem.getQuantity());
            data.put("totalPrice", orderItem.getTotalPrice());
            data.put("status", orderItem.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listOrderItem.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var orderItem = orderItemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order item not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("food", orderItem.getFood());
        data.put("quantity", orderItem.getQuantity());
        data.put("totalPrice", orderItem.getTotalPrice());
        data.put("status", orderItem.getStatus());
        return data;
    }
}