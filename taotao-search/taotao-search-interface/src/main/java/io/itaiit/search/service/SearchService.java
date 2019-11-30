package io.itaiit.search.service;

import io.itaiit.common.pojo.SearchResult;
import io.itaiit.common.pojo.TaotaoResult;

public interface SearchService {

    /**
     * 导入所有的商品数据到索引库中
     * @return
     */
    public TaotaoResult importAllSearchItems() throws Exception;

    /**
     * 根据查询条件返回查询结果集
     * @param queryString
     * @param page
     * @param rows 每页显示的行数，在controller中写死
     * @return
     * @throws Exception
     */
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception;

}
