package com.rebwon.pagingoptimizing.repository;

import com.rebwon.pagingoptimizing.domain.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerJdbcRepositoryImpl implements CustomerJdbcRepository {
  private final JdbcTemplate jdbcTemplate;

  @Value("${jdbc.batchSize}")
  private int batchSize;

  @Override
  public void saveAll(List<Customer> customers) {
    int batchCount = 0;
    List<Customer> subCustomers = new ArrayList<>();
    for (int i = 0; i < customers.size(); i++) {
      subCustomers.add(customers.get(i));
      if((i+1) % batchSize == 0) {
        batchCount = batchInsert(batchCount, subCustomers);
      }
    }
    if(!subCustomers.isEmpty()) {
      batchCount = batchInsert(batchCount, subCustomers);
    }
    log.debug("BatchCount: {}", batchCount);
  }

  private int batchInsert(int batchCount, List<Customer> subCustomers) {
    jdbcTemplate.batchUpdate("INSERT INTO CUSTOMER (`NAME`) VALUES(?)",
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            ps.setString(1, subCustomers.get(i).getName());
          }

          @Override
          public int getBatchSize() {
            return subCustomers.size();
          }
        });
    subCustomers.clear();
    batchCount++;
    return batchCount;
  }


}
