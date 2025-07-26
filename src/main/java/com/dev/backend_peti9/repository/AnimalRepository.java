package com.dev.backend_peti9.repository;

import com.dev.backend_peti9.model.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

}
