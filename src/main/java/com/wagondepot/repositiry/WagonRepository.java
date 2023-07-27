package com.wagondepot.repositiry;

import com.wagondepot.entity.wagon.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Long> {
}

