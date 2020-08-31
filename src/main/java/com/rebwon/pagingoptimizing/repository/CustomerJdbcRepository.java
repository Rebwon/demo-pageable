package com.rebwon.pagingoptimizing.repository;

import com.rebwon.pagingoptimizing.domain.Customer;
import java.util.List;

public interface CustomerJdbcRepository {
  void saveAll(List<Customer> customers);
}
