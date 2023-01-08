package com.exam2022.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer_order_items")
@Table(name = "customer_order_items")
public class CustomerOrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_order_id")
    private Long id;

    @Column(name = "item_id")
    private Long item_id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    @Override
    public String toString() {
        return "CustomerOrderItems{" +
                "id=" + id +
                ", item_id=" + item_id +
                '}';
    }
}
