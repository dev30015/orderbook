## Endpoints for Order Book

| Request Type  |URL   | Description |
| ------------ | ------------ | ------------ |
| GET| /orders  | Find all orders |
| GET| /orders/search  | Search order by multiple conditions using the OrderSearchQuery model|
| POST| /orders  | Post a new order |
| DELETE| /orders/{id}  | Delete an order |

## Sample Codes
```java
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
```

       
        
              
## Sample Requests
<details>
<summary>Click here to view sample requests</summary>
              
**OrderSearchQuery**
```json
{
    "type": "",
    "start_price": "",
    "final_price": "",
    "order_placed_date": "",
    "executed":""
}
```
It can search through the database by meeting multiple custom conditions. 
Each field can be optional. 
- If none of the data is supplied, it finds all the record in the database. 
- If a particular type is provided, then it finds related to that type, otherwise all types will be queried. 
- If start price provided & final price is not provided, then it finds all the prices >= start price
- If start price is not provided & final price is provided, then it finds all the prices <= final price
- If start price is provided & final price is also provided, then it finds all the prices between the start price and the final price
- If both start price & final price are not provided, then it finds all the prices
- If order place date is provided then it finds the orders that matches the date, otherwise it matches all.
- If order execution status is provided then it finds the orders that matches the execution status, otherwise it matches all. 


Sample request
Search all the orders that are buy orders within the price range of 120 to 5000. 
```json
{
    "type": "Buy order",
    "start_price": "120",
    "final_price": "5000",
    "order_placed_date": "",
    "executed":""
}
```

Reponses
```json
[
    {
        "id": 2,
        "content": "Buy order",
        "price": 150.0,
        "orderPlaced": "2000-02-01T13:00:00.000+00:00",
        "executedDate": "2000-02-01T13:00:00.000+00:00",
        "executed": true
    },
    {
        "id": 7,
        "content": "Buy order",
        "price": 350.0,
        "orderPlaced": "2001-02-01T13:00:00.000+00:00",
        "executedDate": "2001-02-01T13:00:00.000+00:00",
        "executed": true
    },
    {
        "id": 11,
        "content": "Buy order",
        "price": 1000.0,
        "orderPlaced": "2002-01-01T13:00:00.000+00:00",
        "executedDate": "2002-01-01T13:00:00.000+00:00",
        "executed": true
    },
    {
        "id": 12,
        "content": "Buy order",
        "price": 1500.0,
        "orderPlaced": "2002-02-01T13:00:00.000+00:00",
        "executedDate": "2002-02-01T13:00:00.000+00:00",
        "executed": true
    },
    {
        "id": 16,
        "content": "Buy order",
        "price": 1300.0,
        "orderPlaced": "2002-01-01T13:00:00.000+00:00",
        "executedDate": "2002-01-01T13:00:00.000+00:00",
        "executed": true
    },
    {
        "id": 17,
        "content": "Buy order",
        "price": 500.0,
        "orderPlaced": "2002-02-01T13:00:00.000+00:00",
        "executedDate": "2002-02-01T13:00:00.000+00:00",
        "executed": true
    }
]
```
</details>

