package com.wagondepot.repositiry;

import com.wagondepot.entity.station.Way;
import com.wagondepot.entity.wagon.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WayRepository extends JpaRepository<Way, Long> {
    @Query("SELECT w FROM Way w JOIN w.positionWagons pw JOIN pw.wagon wagon WHERE wagon.id = :wagonId")
    Optional<Way> findByWagonId(@Param("wagonId") Long wagonId);
}

