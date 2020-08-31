package com.rebwon.pagingoptimizing.domain;

import com.rebwon.pagingoptimizing.api.CustomerDto;
import com.rebwon.pagingoptimizing.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  public Page<CustomerDto> getCustomer(Pageable pageable, Integer totalElements) {
    Page<Customer> customers = customerRepository.findAll(pageable, totalElements);
    return customers.map(CustomerDto::of);
  }
}