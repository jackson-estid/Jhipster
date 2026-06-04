package com.mycompany.ejemplo04.repository;

import com.mycompany.ejemplo04.domain.Tabla;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Tabla entity.
 */
@Repository
public interface TablaRepository extends MongoRepository<Tabla, String> {}
