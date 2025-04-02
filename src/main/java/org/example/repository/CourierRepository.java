package org.example.repository;

import org.example.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    boolean existsByPersonalCode(String personalCode);
}
