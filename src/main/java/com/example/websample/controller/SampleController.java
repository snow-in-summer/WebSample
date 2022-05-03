package com.example.websample.controller;

import com.example.websample.dto.ErrorResponse;
import com.example.websample.exception.WebSampleException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException {
        log.info("Get some order : " + id);

        if ("500".equals(id)) {
            throw new IllegalAccessException("500 is not valid orderId.");
        }

        if ("700".equals(id)) {
            throw new ArithmeticException("500 is not valid orderId.");
        }

        if("900".equals(id)) {
            throw new WebSampleException("900 Error", "900 input inserted.");
        }

        return "orderId:" + id + ", " + "orderAmount:1000";
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order : " + id);
        return "Delete orderId:" + id;
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") Integer amount) {
        log.info("Get order : " + id + ", amount : " + amount);
        return "orderId:" + id + ", " + "orderAmount:" + amount;
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Create order : " + createOrderRequest +
                ", userAccountId : " + userAccountId);
        return "orderId:" + createOrderRequest.getOrderId() + ", " +
                "orderAmount:" + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessException.class)
    public ErrorResponse handleIllegalAccessException(IllegalAccessException e) {
        log.error("Illegal Exception : ", e);

        return new ErrorResponse("ACCESS_DENIED", "Illegal Exception occurred.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Internal Server Exception : ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "INTERNAL_SERVER_ERROR",
                        "Illegal Exception occurred."
                ));
    }

    @Data
    @AllArgsConstructor
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}
