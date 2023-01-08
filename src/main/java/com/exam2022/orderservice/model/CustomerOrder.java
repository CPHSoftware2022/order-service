package com.exam2022.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "customer_order")
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_id")
    public Long id;

    @Column(name = "order_start")
    public Date order_start;

    @Column(name = "order_end")
    public Date order_end;

    @Column(name = "total_price")
    public double total_price;

    @Column(name = "accepted")
    public boolean accepted;

    @Column(name = "canceled_reason")
    public String canceled_reason;

    @Column(name = "delivered")
    public boolean delivered;

    @Column(name = "customer_id")
    public Long customer_id;

    @Column(name = "restaurant_id")
    public Long restaurant_id;

    @Column(name = "feedback_id")
    public Long feedback_id;

    @Column(name = "employee_id")
    public Long employee_id;

    public CustomerOrder(Date order_start, Date order_end, double total_price, boolean accepted, String canceled_reason, boolean delivered, Long customer_id, Long restaurant_id, Long feedback_id, Long employee_id) {
        this.order_start = order_start;
        this.order_end = order_end;
        this.total_price = total_price;
        this.accepted = accepted;
        this.canceled_reason = canceled_reason;
        this.delivered = delivered;
        this.customer_id = customer_id;
        this.restaurant_id = restaurant_id;
        this.feedback_id = feedback_id;
        this.employee_id = employee_id;
    }
}
