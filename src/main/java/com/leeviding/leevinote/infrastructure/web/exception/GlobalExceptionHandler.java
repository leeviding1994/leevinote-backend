package com.leeviding.leevinote.infrastructure.web.exception;

import com.leeviding.leevinote.infrastructure.web.response.Msg;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理JWT令牌过期异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Msg<?>> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Msg.failure("Token已过期"));
    }

    /**
     * 处理JWT令牌格式错误异常
     */
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Msg<?>> handleMalformedJwtException(MalformedJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Msg.failure("Token格式错误"));
    }

    /**
     * 处理JWT令牌签名无效异常
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Msg<?>> handleSignatureException(SignatureException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Msg.failure("Token签名无效"));
    }

    /**
     * 处理JWT令牌参数错误异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Msg<?>> handleIllegalArgumentException(IllegalArgumentException e) {
        if (e.getMessage() != null && e.getMessage().contains("JWT")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Msg.failure("Token参数错误"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Msg.failure(e.getMessage()));
    }

    /**
     * 处理资源不存在异常
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Msg<?>> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Msg.failure("资源不存在"));
    }

    /**
     * 处理权限不足异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Msg<?>> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Msg.failure("权限不足"));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Msg<?>> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus())
                .body(Msg.failure(e.getMessage()));
    }

    /**
     * 处理其他未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Msg<?>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Msg.failure(e.getMessage()));
    }
}
