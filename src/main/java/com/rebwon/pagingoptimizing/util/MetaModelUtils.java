package com.rebwon.pagingoptimizing.util;

import com.querydsl.core.types.Path;

public class MetaModelUtils {
  private MetaModelUtils() {}

  public static String getColumn(Path<?> path) {
    return path.getMetadata().getName();
  }
}
