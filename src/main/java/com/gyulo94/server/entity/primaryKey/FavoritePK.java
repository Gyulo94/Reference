package com.gyulo94.server.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePK implements Serializable {
  @Column(name = "user_email")
  private String userEmail;
  @Column(name = "board_number")
  private Long boardNumber;
}
