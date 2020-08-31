package com.rebwon.pagingoptimizing;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

  public BaseRepositoryImpl(
      JpaEntityInformation<T, ?> entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  @Override
  public Page<T> findAll(Pageable pageable, Integer totalElements) {
    return findAll(null, pageable, totalElements, Sort.unsorted());
  }

  @Override
  public Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalElements) {
    return findAll(spec, pageable, totalElements, Sort.unsorted());
  }

  @Override
  public Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalElements,
      Sort sort) {
    TypedQuery<T> query = getQuery(spec, sort);
    int pageNumber = pageable.getPageNumber();
    int pageSize = pageable.getPageSize();

    if(totalElements == null) {
      return findAll(spec, pageable);
    }
    if(pageNumber < 0) {
      throw new IllegalArgumentException("Page number must not be less than zero");
    }
    if(pageSize < 1) {
      throw new IllegalArgumentException("PageSize must not be less than one");
    }
    if(totalElements < pageNumber * pageSize) {
      throw new IllegalArgumentException("TotalElements must not be less than pageNumber * pageSize");
    }
    int offset = (int) pageable.getOffset();
    query.setFirstResult(offset);
    query.setMaxResults(pageable.getPageSize());
    return new PageImpl<>(query.getResultList(), PageRequest.of(pageNumber, pageSize), totalElements);
  }
}
