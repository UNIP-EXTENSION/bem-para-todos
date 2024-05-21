package br.ong.bemparatodos.bemparatodos.util.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public final class PageUtils {

  private PageUtils() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static <T> Page<T> convertCollectionToPage(Collection<T> collection, Pageable pageable) {
    List<T> list = collection.stream().toList();

    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;

    List<T> pageContent;
    if (list.size() < startItem) {
      pageContent = List.of();
    } else {
      int toIndex = Math.min(startItem + pageSize, list.size());
      pageContent = list.subList(startItem, toIndex);
    }

    return new PageImpl<>(pageContent, pageable, list.size());
  }
}
