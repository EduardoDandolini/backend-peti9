package com.dev.backend_peti9.repository;

import com.dev.backend_peti9.model.Vaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Long> {

}
