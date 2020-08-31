package com.rebwon.pagingoptimizing;

import java.util.List;

public interface CustomerJdbcRepository {
  void saveAll(List<Customer> customers);
}
