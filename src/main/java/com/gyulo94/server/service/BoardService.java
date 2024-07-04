package com.gyulo94.server.service;

import org.springframework.http.ResponseEntity;

import com.gyulo94.server.dto.request.board.PostBoardRequestDto;
import com.gyulo94.server.dto.request.board.PostCommentRequestDto;
import com.gyulo94.server.dto.response.board.DeleteBoardResponseDto;
import com.gyulo94.server.dto.response.board.GetBoardResponseDto;
import com.gyulo94.server.dto.response.board.GetCommentListResponseDto;
import com.gyulo94.server.dto.response.board.GetFavoriteListResponseDto;
import com.gyulo94.server.dto.response.board.IncreaseViewCountResponseDto;
import com.gyulo94.server.dto.response.board.PostBoardResponseDto;
import com.gyulo94.server.dto.response.board.PostCommentResponseDto;
import com.gyulo94.server.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
  ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardNumber);

  ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardNumber);

  ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardNumber);

  ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

  ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Long boardNumber, String email);

  ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardNumber, String email);

  ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Long boardNumber);

  ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNumber, String email);
}