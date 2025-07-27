package com.dev.backend_peti9.repository;

import com.dev.backend_peti9.model.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE LOWER(a.name) LIKE %:name%")
    List<Animal> findByLikedName(@Param("name") String name);

    boolean existsByName(String name);

}
