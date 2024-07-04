package com.gyulo94.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyulo94.server.entity.ImageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

  List<ImageEntity> findByBoardNumber(Long boardNumber);

  @Transactional
  void deleteByBoardNumber(Long boardNumber);

}
