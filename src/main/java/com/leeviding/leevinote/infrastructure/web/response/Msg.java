package com.leeviding.leevinote.infrastructure.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg<T> {

    private MsgStatus status;

    @JsonInclude(NON_NULL)
    private String error;

    @JsonInclude(NON_NULL)
    private T data;

    public static <T> Msg<T> success(T data) {
        return new Msg<>(MsgStatus.SUCCESS, null, data);
    }

    public static <T> Msg<T> success() {
        return new Msg<>(MsgStatus.SUCCESS, null, null);
    }

    public static <T> Msg<T> failure(String error) {
        return new Msg<>(MsgStatus.FAILURE, error, null);
    }
}