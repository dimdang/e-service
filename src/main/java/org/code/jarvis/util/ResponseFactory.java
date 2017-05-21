package org.code.jarvis.util;

import org.code.jarvis.model.response.JResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Created by KimChheng on 5/9/2017.
 */
public final class ResponseFactory {

    public static <T> JResponseEntity<T> build() {
        return new JResponseEntity();
    }

    public static <T> JResponseEntity<T> build(String message) {
        return ResponseFactory.build(message, null);
    }

    public static <T> JResponseEntity<T> build(String message, HttpStatus httpStatus) {
        return ResponseFactory.build(message, httpStatus, null);
    }

    public static <T> JResponseEntity<T> build(String message, HttpStatus status, T body) {
        JResponseEntity<T> responseEntity = build();
        responseEntity.setMessage(message);
        responseEntity.setStatus(status);
        responseEntity.addBody(body);
        return responseEntity;
    }


}
