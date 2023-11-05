package com.caffeBar.caffe.repo;

import com.caffeBar.caffe.model.CaffeBarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CaffeBarAdmin, Long> {
    List<CaffeBarAdmin> findAll();

    Optional<CaffeBarAdmin> findByUsername(String username);
}
