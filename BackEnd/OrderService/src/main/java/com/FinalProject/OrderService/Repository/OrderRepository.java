package com.FinalProject.OrderService.Repository;

import com.FinalProject.OrderService.Domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

//@EnableMongoRepositories
@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
}
