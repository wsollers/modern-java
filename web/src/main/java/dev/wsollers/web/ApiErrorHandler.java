package dev.wsollers.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

import java.net.URI;

@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAny(Exception ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error");
        pd.setDetail("An unexpected error occurred.");
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("requestId", MDC.get("requestId"));
        return pd;
    }
}
