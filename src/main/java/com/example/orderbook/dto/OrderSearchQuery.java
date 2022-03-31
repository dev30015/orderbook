package com.example.orderbook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class OrderSearchQuery {

    @JsonProperty("type")
    private String type;

    @JsonProperty("start_price")
    private String startPrice;

    @JsonProperty("final_price")
    private String finalPrice;

    @JsonProperty("order_placed_date")
    private String orderPlacedDate;

    @JsonProperty("executed")
    private Boolean executed;
}
