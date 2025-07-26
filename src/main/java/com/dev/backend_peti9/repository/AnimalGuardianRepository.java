package com.dev.backend_peti9.repository;

import com.dev.backend_peti9.model.AnimalGuardian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalGuardianRepository extends CrudRepository<AnimalGuardian, Long> {

    boolean existByName(String name);

    Optional<AnimalGuardian> findByLikedName(String name);

}
