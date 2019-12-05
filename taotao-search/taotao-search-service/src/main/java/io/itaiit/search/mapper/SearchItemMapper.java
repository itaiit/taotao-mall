package io.itaiit.search.mapper;

import io.itaiit.common.pojo.SearchItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定义mapper，关联查询3张表，查询出搜索时的商品数据
 */
public interface SearchItemMapper {

    List<SearchItem> getSearchItemList();

    SearchItem getSearchItemById(@Param("id") Long id);
}
