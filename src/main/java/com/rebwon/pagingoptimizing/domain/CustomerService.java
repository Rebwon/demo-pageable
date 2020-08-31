package com.rebwon.pagingoptimizing.domain;

import com.rebwon.pagingoptimizing.api.CustomerDto;
import com.rebwon.pagingoptimizing.api.PageableDto;
import com.rebwon.pagingoptimizing.repository.CustomerRepository;
import com.rebwon.pagingoptimizing.util.CustomerMetaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private DomainSpec<CustomerMetaType, Customer> spec;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.spec = new DomainSpec<>(CustomerMetaType.class, new CustomerSortStrategy());
  }

  public Page<CustomerDto> getCustomer(PageableDto dto) {
    final var pageable = spec.getPageable(dto);
    final var customers = customerRepository.findAll(pageable, dto.getTotalElements());

    return customers.map(CustomerDto::of);
  }

  public Page<CustomerDto> getCustomer(Pageable pageable, Integer totalElements) {
    Page<Customer> customers = customerRepository.findAll(pageable, totalElements);
    return customers.map(CustomerDto::of);
  }
}
