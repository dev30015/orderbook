### JPA Experimental queries (via native SQL)

###How to run
------------
Clone this repository


####Functionality Overview
- CRUD operations for User, Order
- Find orders by user ID
- Find orders by year
- Find orders by price range
- Find orders by order type
- Find orders by order execution date
- Find orders by multiple conditions
                    
###API Endpoints Overview
                    
Request Type |Endpoint  | Description
------------- | -------------
POST|/orders  | Buy Order, Sell Order through the Order Request
GET|/orders/ | List all orders
GET|/orders/{id}  | Find orders by user ID
GET|/orders/year/{year}  | Find orders by year
GET|/orders/year/{year}  | Find orders by price range
GET|/orders/year/{year}  | Find orders by order type
GET|/orders/year/{year}  | Find orders by order execution date

####Post Order
```json
POST /orders
Accept: application/json
Content-Type: application/json

{
"order_type" : "Buy",
"price" : "$350.00",
"order_execution_time" : ""
}
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8090/example/v1/hotels/1
```


###Implementation
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
    Collection<Order> findOrdersByExecutionDate(Calendar date);
}
```

