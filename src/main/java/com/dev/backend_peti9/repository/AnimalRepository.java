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

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Animal a WHERE a.name = :name AND a.animalGuardian.id = :guardianId")
    boolean existsByNameAndAnimalGuardianId(@Param("name") String name, @Param("guardianId") Long guardianId);

}
