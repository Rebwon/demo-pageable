package com.rebwon.pagingoptimizing;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class CustomerDto {
  private Long customerId;
  private String name;

  public static CustomerDto of(Customer customer) {
    return CustomerDto.builder()
        .customerId(customer.getId())
        .name(customer.getName())
        .build();
  }
}
