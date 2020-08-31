package com.rebwon.pagingoptimizing.api;

import com.rebwon.pagingoptimizing.domain.Pair;
import com.rebwon.pagingoptimizing.domain.SortOption;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageableDto {
  private Integer page;
  private Integer size;
  private Integer totalElements;
  private List<Pair<String, SortOption>> sorts;
}
