package com.example.orderbook;

import com.example.orderbook.dto.OrderSearchQuery;
import com.example.orderbook.order.Order;
import com.example.orderbook.order.OrderRepository;
import com.example.orderbook.user.User;
import com.sun.prism.null3d.NULL3DPipeline;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderTest {

    @Resource
    private OrderRepository orderRespo;

    @Test
    public void test() {
        List<Order> orders = orderRespo.findAll();
        assertTrue(orders.size()==20);
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    void findOrderByUser() {
        Collection<Order> orders = orderRespo.findOrdersByUser(2);
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    void findOrderByYear() {
        Collection<Order> orders = orderRespo.findOrdersByYear(2000);
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    void findOrdersByPriceRange() {
        Collection<Order> orders = orderRespo.findOrdersByPriceRange(100.0f,300.0f);
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    void findOrderByType() {
        Collection<Order> orders = orderRespo.findOrdersByType("Buy order");
        System.out.println("Listing all the buy orders...");
        for(Order order:orders) {
            System.out.println(order);
        }

        orders=orderRespo.findOrdersByType("Sell order");
        System.out.println("Listing all the sell orders...");
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    void findOrderByDate() {
        String orderDate = "2000-01-01 13:00:00";
        Collection<Order> orders = orderRespo.findOrderByExecutionDate(orderDate);
        for(Order order: orders) {
            System.out.println(order);
        }
    }

    @Test
    /**
     * The price range (a,b) where a represents the minimum price and b represents the maximum price
     * If a is not supplied then there is no minimum (i.e. checking for all the prices below)
     * If b is not supplied then there is no maximum (i.e. checking for all the prices above)
     * If a and b are not supplied then there is no restriction.
     */
    void testFlexiblePriceRange() {
        Collection<Order> orders = orderRespo.findOrdersByFlexiblePriceRange("100","300");
        assertEquals(6,orders.size());
        System.out.println("Testing all orders from 100 to 300");
        orders.forEach (order -> {
            assertThat(order.getPrice()).isBetween(100f, 300f);
        });

        System.out.println("Testing all orders <= 300");
        orders = orderRespo.findOrdersByFlexiblePriceRange("","300");
        assertEquals(6,orders.size());

        orders.forEach (order -> {
            assertThat(order.getPrice()).isBetween(0f, 300f);
        });

        System.out.println("Testing all orders >= 1000");
        orders = orderRespo.findOrdersByFlexiblePriceRange("1000","");
        assertEquals(6,orders.size());

        orders.forEach (order -> {
            System.out.println(order);
        });
    }

    @Test
    void testByConditions() {
        //OrderSearchQuery query = new OrderSearchQuery(
        //Collection<Order> orders = orderRespo.findOrdersByConditions("Buy order","100","300","2000-01-01 13:00:00",Boolean.TRUE);
        Collection<Order> orders = orderRespo.findOrdersByConditions("Buy order",null,null, null,null);
        for(Order order:orders) {
            System.out.println(order);
        }
    }

    /*
    @Test
    void findOrderById() {
        Optional<Order> targetOrder = orderRespo.findById(1L);
        User expectedUser = new User();
        Order expectedOrder = new Order(1,"Buy Order,",100.0f,new User()
    }
     */
}
