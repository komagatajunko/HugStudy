package com.example.tukatta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tukatta.entity.TukattaEntity;

/**
* 支出情報 Repository
*/
@Repository
public interface TukattaRepository extends JpaRepository<TukattaEntity, Integer>{

}
