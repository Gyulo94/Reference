package com.gyulo94.server.common;

public interface ResponseMessage {

  // HTTP Status 200
  String SUCCESS = "Success.";

  // HTTP Status 400
  String VALIDATION_FAILED = "Validation Failed.";
  String DUPLICATE_EMAIL = "Duplicate Email.";
  String DUPLICATE_NICKNAME = "Duplicated Nickname.";
  String DUPLICATE_TEL_NUMBER = "Duplicated Tel Number.";
  String NOT_EXISTED_USER = "This user does not exist.";
  String NOT_EXISTED_BOARD = "This board does not exist.";

  // HTTP Status 401
  String SIGN_IN_FAILED = "Login Information Mismatch.";
  String AUTHORIZATION_FAILED = "Authorization Failed.";

  // HTTP Status 403
  String NO_PERMISSION = "Do not have Permission.";

  // HTTP Status 500
  String DATABASE_ERROR = "Database Error.";
}
