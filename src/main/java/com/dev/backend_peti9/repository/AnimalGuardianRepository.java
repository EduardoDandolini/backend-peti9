package com.dev.backend_peti9.repository;

import com.dev.backend_peti9.model.AnimalGuardian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalGuardianRepository extends CrudRepository<AnimalGuardian, Long> {

    boolean existsByName(String name);

    @Query("SELECT a FROM AnimalGuardian a WHERE LOWER(a.name) LIKE %:name%")
    Optional<AnimalGuardian> findByLikedName(@Param("name") String name);

}
