package com.rebwon.pagingoptimizing.domain;

import com.rebwon.pagingoptimizing.api.CustomerDto;
import com.rebwon.pagingoptimizing.api.PageableDto;
import com.rebwon.pagingoptimizing.config.CachingConfig;
import com.rebwon.pagingoptimizing.repository.CustomerRepository;
import com.rebwon.pagingoptimizing.util.CustomerMetaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private DomainSpec<CustomerMetaType, Customer> spec;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.spec = new DomainSpec<>(CustomerMetaType.class, new CustomerSortStrategy());
  }

  @Cacheable(value = CachingConfig.USER_CACHE, key = "#id")
  public CustomerDto getCustomer(Long id) {
    log.info("getCustomer from db");
    Customer customer = customerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return CustomerDto.of(customer);
  }

  public Page<CustomerDto> findAll(PageableDto dto) {
    final var pageable = spec.getPageable(dto);
    final var customers = customerRepository.findAll(pageable, dto.getTotalElements());

    return customers.map(CustomerDto::of);
  }

  public Page<CustomerDto> findAll(Pageable pageable, Integer totalElements) {
    Page<Customer> customers = customerRepository.findAll(pageable, totalElements);
    return customers.map(CustomerDto::of);
  }
}
