package edu.icet.repository;


import edu.icet.entity.RegisterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<RegisterEntity,Long> {
    RegisterEntity findByEmail(String email);
    List<RegisterEntity> findByOrderByCreatedDateDesc(Pageable pageable);
}