package com.example.orderbook.order;

import com.example.orderbook.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value="select * from orders o where o.ordered_by =?1",nativeQuery=true)
    Collection<Order> findOrdersByUser(int userID);

    @Query(value="select * from orders o where year(o.order_placed) = ?1",nativeQuery=true)
    Collection<Order> findOrdersByYear(int year);

    @Query(value="select * from orders o where o.price >=?1 and o.price <= ?2",nativeQuery=true)
    Collection<Order> findOrdersByPriceRange(float startPrice, float endPrice);

    @Query(value="select * from orders o where o.content=?1",nativeQuery=true)
    Collection<Order> findOrdersByType(String type);

    @Query(value="select * from orders o where o.order_execution_date=?1",nativeQuery=true)
    Collection<Order> findOrderByExecutionDate(String date);

    //Style1: @Query(value="select * from orders o where (?1 is null or ?1 = '' or o.price>=?1) and (?2 is null or ?2 = '' or o.price<=?2)",nativeQuery=true)
    //Style 2
    @Query(value="select * from orders o where (:startPrice is null or :startPrice = '' or o.price>=:startPrice) " +
            "and (:finalPrice is null or :finalPrice = '' or o.price<=:finalPrice)",nativeQuery=true)
    Collection<Order> findOrdersByFlexiblePriceRange(@Param("startPrice")String startPrice,@Param("finalPrice")String finalPrice);

    @Query(value="select * from orders o " +
            "where (:content is null or :content = '' or o.content=:content) " +
            "and (:startPrice is null or :startPrice = '' or o.price>=:startPrice)" +
            "and (:endPrice is null or :endPrice = '' or o.price<=:endPrice)" +
            "and (:orderPlacedDate is null or o.order_placed=:orderPlacedDate)" +
            "and (:isExecuted is null or o.executed=:isExecuted)",nativeQuery=true)
    Collection<Order> findOrdersByConditions(@Param("content") String content, @Param("startPrice")String startPrice, @Param("endPrice")String endPrice, @Param("orderPlacedDate")String orderPlacedDate, @Param("isExecuted")Boolean isExecuted);
}
