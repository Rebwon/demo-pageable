package com.rebwon.pagingoptimizing.domain;

import com.rebwon.pagingoptimizing.config.CachingConfig;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity @NoArgsConstructor @Getter @Cacheable
@org.hibernate.annotations.Cache(region = CachingConfig.DB_CACHE, usage = CacheConcurrencyStrategy.READ_ONLY)
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public Customer(String name) {
    this.name = name;
  }
}
