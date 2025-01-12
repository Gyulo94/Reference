package com.gyulo94.server.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
  private int boardNumber;
  private String title;
  private String content;
  private String boardTitleImage;
  private String favoriteCount;
  private String commentCount;
  private int viewCount;
  private String writeDatetime;
  private String writerNickname;
  private String writerProfileImage;
}
