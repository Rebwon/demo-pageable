package com.rebwon.pagingoptimizing;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("/customers")
  public ResponseEntity<Page<CustomerDto>> getCustomer(Pageable pageable,
      @RequestParam(required = false, name = "total_elements") Integer totalElements) {
    Page<CustomerDto> customer = customerService.getCustomer(pageable, totalElements);
    return ResponseEntity.ok(customer);
  }
}
