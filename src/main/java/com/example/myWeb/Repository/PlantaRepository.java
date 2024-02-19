package com.example.myWeb.Repository;

import com.example.myWeb.Entity.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantaRepository extends JpaRepository<Planta,Long> {
}
