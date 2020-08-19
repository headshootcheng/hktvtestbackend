package com.example.demo.respository;


import com.example.demo.modal.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    List<Location> findByName(String name);
}
