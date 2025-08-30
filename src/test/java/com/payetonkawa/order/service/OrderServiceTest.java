package com.payetonkawa.order.service;

import com.payetonkawa.order.exception.ResourceNotFoundException;
import com.payetonkawa.order.model.Order;
import com.payetonkawa.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setClientId(100L);
        order.setDateCommande(LocalDate.now());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(1, orders.size());
        assertEquals(100L, orders.get(0).getClientId());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById_Found() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order found = orderService.getOrderById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderById(99L));
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);

        Order created = orderService.createOrder(order);

        assertNotNull(created);
        assertEquals(100L, created.getClientId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testUpdateOrder() {
        Order updatedDetails = new Order();
        updatedDetails.setClientId(200L);
        updatedDetails.setDateCommande(LocalDate.now());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updated = orderService.updateOrder(1L, updatedDetails);

        assertNotNull(updated);
        assertEquals(200L, updated.getClientId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).delete(order);
    }
}
