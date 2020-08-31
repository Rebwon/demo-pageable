package com.rebwon.pagingoptimizing.domain;

import com.rebwon.pagingoptimizing.util.CustomerMetaType;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class CustomerSortStrategy implements SortStrategy<CustomerMetaType> {

  @Override
  public Order getSortOrder(CustomerMetaType domainKey, SortOption order) {
    Sort.Order sortOrder = null;
    switch (domainKey) {
      case CUSTOMER_ID:
      case CUSTOMER_NAME:
        final var column = domainKey.getName();
        sortOrder = (order == SortOption.ASC) ? Sort.Order.asc(column) : Sort.Order.desc(column);
    }
    return sortOrder;
  }
}
