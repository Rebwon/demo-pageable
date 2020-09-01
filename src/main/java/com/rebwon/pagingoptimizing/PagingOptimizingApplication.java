package com.rebwon.pagingoptimizing;

import com.rebwon.pagingoptimizing.domain.Customer;
import com.rebwon.pagingoptimizing.repository.BaseRepositoryImpl;
import com.rebwon.pagingoptimizing.repository.CustomerJdbcRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class PagingOptimizingApplication {

  public static void main(String[] args) {
    SpringApplication.run(PagingOptimizingApplication.class, args);
  }

  @Component
  public static class JpaRunner implements ApplicationRunner {
    @Autowired
    private CustomerJdbcRepository customerJdbcRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
      List<Customer> customers = new ArrayList<>();
      for (int i = 0; i < 99; i++) {
        customers.add(new Customer("name" + i));
      }
      customerJdbcRepository.saveAll(customers);
    }
  }
}
