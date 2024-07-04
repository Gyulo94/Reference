package com.gyulo94.server.repository.resultSet;

public interface GetBoardResultSet {
  Long getBoardNumber();

  String getTitle();

  String getContent();

  String getWriteDatetime();

  String getWriterEmail();

  String getWriterNickname();

  String getWriterProfileImage();
}
