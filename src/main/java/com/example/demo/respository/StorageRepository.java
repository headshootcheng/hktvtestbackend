package com.example.demo.respository;


import com.example.demo.modal.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Integer> {
    List<Storage> findByCodeAndLocation (String code, String location);
}
