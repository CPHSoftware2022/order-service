package com.exam2022.orderservice.repository;

import com.exam2022.orderservice.model.CustomerOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderItemsRepository extends JpaRepository<CustomerOrderItems, Long> {
}
