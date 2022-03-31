package com.example.orderbook.order;

import com.example.orderbook.dto.OrderSearchQuery;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository=orderRepository;
    }

    @GetMapping("/orders")
    List<Order> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @DeleteMapping("/orders/{id}")
    void deleteNote(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }


    @GetMapping("/orders/search")
    Collection<Order> searchOrdersByCriterias(@RequestBody OrderSearchQuery query) {
        //OrderSearchQuery newQuery = new OrderSearchQuery();
//        if (query.getOrderPlacedDate().isEmpty()) {
//            return orderRepository.findOrdersByConditions(query.getType(),query.getStartPrice(),query.getFinalPrice(),"",query.isExecuted());
//        }
            //        return orderRepository.findOrdersByConditions(query.getType(),query.getStart_price(),query.getFinal_price(),query.getOrder_placed_date(),query.isExecuted());
        return orderRepository.findOrdersByConditions(query.getType(),query.getStartPrice(),query.getFinalPrice(),query.getOrderPlacedDate(),query.getExecuted());
    }


//    @GetMapping("/orders/test")
//    Collection<Order> test(@RequestBody OrderSearchQuery query) {
//        return orderRepository.testBy2Conditions(query.getType(),query.getStartPrice());
//    }

}
