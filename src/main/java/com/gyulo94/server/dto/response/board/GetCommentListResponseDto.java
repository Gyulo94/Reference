package com.gyulo94.server.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gyulo94.server.common.ResponseCode;
import com.gyulo94.server.common.ResponseMessage;
import com.gyulo94.server.dto.object.CommentListItem;
import com.gyulo94.server.dto.response.ResponseDto;
import com.gyulo94.server.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

  private List<CommentListItem> commentList;

  public GetCommentListResponseDto(List<GetCommentListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commentList = CommentListItem.copyList(resultSets);
  }

  public static ResponseEntity<GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSets) {
    GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
