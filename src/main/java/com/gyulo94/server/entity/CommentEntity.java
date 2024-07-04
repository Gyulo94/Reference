package com.gyulo94.server.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.gyulo94.server.dto.request.board.PostCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentNumber;
  private String content;
  private String writeDatetime;
  private String userEmail;
  private Long boardNumber;

  public CommentEntity(PostCommentRequestDto dto, Long boardNumber, String email) {

    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.content = dto.getContent();
    this.writeDatetime = writeDatetime;
    this.boardNumber = boardNumber;
    this.userEmail = email;
  }
}
