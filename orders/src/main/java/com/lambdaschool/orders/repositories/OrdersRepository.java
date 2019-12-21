package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

//interface for order crud
public interface OrdersRepository extends CrudRepository<Order, Long> {
}
