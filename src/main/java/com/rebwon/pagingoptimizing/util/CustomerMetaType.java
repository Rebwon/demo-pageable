package com.rebwon.pagingoptimizing.util;

import static com.rebwon.pagingoptimizing.domain.QCustomer.customer;
import static com.rebwon.pagingoptimizing.util.MetaModelUtils.getColumn;

public enum CustomerMetaType {
  CUSTOMER_ID(getColumn(customer.id)),
  CUSTOMER_NAME(getColumn(customer.name));

  private String name;

  CustomerMetaType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
