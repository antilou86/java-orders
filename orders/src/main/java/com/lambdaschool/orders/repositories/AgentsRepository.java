package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

//interface for agent crud
public interface AgentsRepository extends CrudRepository<Agent, Long> {

}