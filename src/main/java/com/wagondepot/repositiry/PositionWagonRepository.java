package com.wagondepot.repositiry;

import com.wagondepot.entity.station.PositionWagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionWagonRepository extends JpaRepository<PositionWagon, Long> {
    PositionWagon findPositionWagonByWagon_Id(Long id);
}

