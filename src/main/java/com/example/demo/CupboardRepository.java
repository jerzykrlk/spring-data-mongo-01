package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CupboardRepository extends CrudRepository<Cupboard, String>, CupboardRepositoryCustom {
}
