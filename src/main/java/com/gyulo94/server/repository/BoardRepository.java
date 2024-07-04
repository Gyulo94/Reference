package com.gyulo94.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gyulo94.server.entity.BoardEntity;
import com.gyulo94.server.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

  @Query(value = "SELECT " + "B.board_number AS boardNumber, " + "B.title AS title, " + "B.content AS content, "
      + "B.write_datetime AS writeDatetime, " + "B.writer_email AS writerEmail, " + "U.nickname AS writerNickname, "
      + "U.profile_image AS writerProfileImage " + "FROM board AS B "
      + "INNER JOIN user AS U " + "ON B.writer_email = U.email "
      + "WHERE board_number = :boardNumber", nativeQuery = true)

  GetBoardResultSet getBoard(Long boardNumber);

  BoardEntity findByBoardNumber(Long boardNumber);

  boolean existsByBoardNumber(Long boardNumber);
}
