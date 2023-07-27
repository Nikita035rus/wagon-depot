package com.wagondepot.repositiry;

import com.wagondepot.entity.ScaleSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScaleSheetRepository extends JpaRepository<ScaleSheet, Long> {
}

