package com.gyulo94.server.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gyulo94.server.dto.request.board.PostBoardRequestDto;
import com.gyulo94.server.dto.request.board.PostCommentRequestDto;
import com.gyulo94.server.dto.response.ResponseDto;
import com.gyulo94.server.dto.response.board.DeleteBoardResponseDto;
import com.gyulo94.server.dto.response.board.GetBoardResponseDto;
import com.gyulo94.server.dto.response.board.GetCommentListResponseDto;
import com.gyulo94.server.dto.response.board.GetFavoriteListResponseDto;
import com.gyulo94.server.dto.response.board.IncreaseViewCountResponseDto;
import com.gyulo94.server.dto.response.board.PostBoardResponseDto;
import com.gyulo94.server.dto.response.board.PostCommentResponseDto;
import com.gyulo94.server.dto.response.board.PutFavoriteResponseDto;
import com.gyulo94.server.entity.BoardEntity;
import com.gyulo94.server.entity.CommentEntity;
import com.gyulo94.server.entity.FavoriteEntity;
import com.gyulo94.server.entity.ImageEntity;
import com.gyulo94.server.repository.BoardRepository;
import com.gyulo94.server.repository.CommentRepository;
import com.gyulo94.server.repository.FavoriteRepository;
import com.gyulo94.server.repository.ImageRepository;
import com.gyulo94.server.repository.UserRepository;
import com.gyulo94.server.repository.resultSet.GetBoardResultSet;
import com.gyulo94.server.repository.resultSet.GetCommentListResultSet;
import com.gyulo94.server.repository.resultSet.GetFavoriteListResultSet;
import com.gyulo94.server.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final ImageRepository imageRepository;
  private final FavoriteRepository favoriteRepository;
  private final CommentRepository commentRepository;

  @Override
  public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
    try {

      boolean existedEmail = userRepository.existsByEmail(email);
      if (!existedEmail)
        return PostBoardResponseDto.noExistUser();
      BoardEntity boardEntity = new BoardEntity(dto, email);
      boardRepository.save(boardEntity);

      List<String> boardImageList = dto.getBoardImageList();
      List<ImageEntity> imageEntities = new ArrayList<>();

      Long boardNumber = boardEntity.getBoardNumber();
      for (String image : boardImageList) {
        ImageEntity imageEntity = new ImageEntity(boardNumber, image);
        imageEntities.add(imageEntity);
      }

      imageRepository.saveAll(imageEntities);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return PostBoardResponseDto.success();
  }

  @Override
  public ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardNumber) {
    GetBoardResultSet resultSet = null;
    List<ImageEntity> imageEntities = new ArrayList<>();
    try {
      resultSet = boardRepository.getBoard(boardNumber);
      if (resultSet == null)
        return GetBoardResponseDto.noExistBoard();
      imageEntities = imageRepository.findByBoardNumber(boardNumber);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetBoardResponseDto.success(resultSet, imageEntities);
  }

  @Override
  public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardNumber, String email) {

    try {
      boolean existedUser = userRepository.existsByEmail(email);
      if (!existedUser)
        return PutFavoriteResponseDto.noExistUser();

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null)
        return PutFavoriteResponseDto.noExistBoard();

      FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
      if (favoriteEntity == null) {
        favoriteEntity = new FavoriteEntity(email, boardNumber);
        favoriteRepository.save(favoriteEntity);
        boardEntity.increaseFavoriteCount();

      } else {
        favoriteRepository.delete(favoriteEntity);
        boardEntity.decreaseFavoriteCount();
      }

      boardRepository.save(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return PutFavoriteResponseDto.success();
  }

  @Override
  public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardNumber) {

    List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!existedBoard)
        return GetFavoriteListResponseDto.noExistBoard();

      resultSets = favoriteRepository.getFavoriteList(boardNumber);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetFavoriteListResponseDto.success(resultSets);
  }

  @Override
  public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Long boardNumber,
      String email) {
    try {

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null)
        return PostCommentResponseDto.noExistBoard();
      boolean existedUser = userRepository.existsByEmail(email);
      if (!existedUser)
        return PostCommentResponseDto.noExistUser();

      CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
      commentRepository.save(commentEntity);

      boardEntity.increaseCommentCount();
      boardRepository.save(boardEntity);

    } catch (

    Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return PostCommentResponseDto.success();
  }

  @Override
  public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardNumber) {

    List<GetCommentListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!existedBoard)
        return GetCommentListResponseDto.noExistBoard();

      resultSets = commentRepository.getCommentList(boardNumber);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetCommentListResponseDto.success(resultSets);
  }

  @Override
  public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Long boardNumber) {
    try {

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null)
        return IncreaseViewCountResponseDto.noExistBoard();
      boardEntity.increaseViewCount();
      boardRepository.save(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return IncreaseViewCountResponseDto.success();
  }

  @Override
  public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNumber, String email) {

    try {

      boolean existedUser = userRepository.existsByEmail(email);
      if (!existedUser)
        return DeleteBoardResponseDto.noExistUser();

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null)
        return DeleteBoardResponseDto.noExistBoard();

      String writerEmail = boardEntity.getWriterEmail();
      boolean isWriter = writerEmail.equals(email);
      if (!isWriter)
        return DeleteBoardResponseDto.noPermission();

      imageRepository.deleteByBoardNumber(boardNumber);
      commentRepository.deleteByBoardNumber(boardNumber);
      favoriteRepository.deleteByBoardNumber(boardNumber);
      boardRepository.delete(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return DeleteBoardResponseDto.success();
  }

}
