package com.rebwon.pagingoptimizing;

import com.rebwon.pagingoptimizing.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class PagingOptimizingApplication {

  public static void main(String[] args) {
    SpringApplication.run(PagingOptimizingApplication.class, args);
  }

}
