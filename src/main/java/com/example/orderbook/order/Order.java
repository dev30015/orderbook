package com.example.orderbook.order;

import com.example.orderbook.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="oid")
    private long id;

    @Column(name="content")
    private String content;

    @Column(name="price")
    private float price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordered_by")
    private User orderedBy;

    @Column(name="order_placed")
    private Calendar orderPlaced;

    @Column(name="order_execution_date")
    private Calendar executedDate;

    @Column(name="executed")
    private boolean executed;

    public Order() {
    }

    public Order(long id, String content, float price, User orderedBy, Calendar orderPlaced, Calendar executedDate, boolean executed) {
        this.id = id;
        this.content = content;
        this.price = price;
        this.orderedBy = orderedBy;
        this.orderPlaced = orderPlaced;
        this.executedDate = executedDate;
        this.executed = executed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public Calendar getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Calendar orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Calendar getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Calendar executedDate) {
        this.executedDate = executedDate;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, price, orderedBy, orderPlaced, executedDate, executed);
    }

    @Override
    public String toString() {
        if (orderPlaced == null || executedDate == null) {
            return "Order{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", price=" + price +
                    ", orderedBy=" + orderedBy +
                    ", orderPlaced=" +  orderPlaced +
                    ", executedDate=" + executedDate +
                    ", executed=" + executed +
                    '}';
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return "Order{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", orderedBy=" + orderedBy +
                ", orderPlaced=" +  format1.format(orderPlaced.getTime()) +
                ", executedDate=" + format1.format(executedDate.getTime()) +
                ", executed=" + executed +
                '}';
    }
}
