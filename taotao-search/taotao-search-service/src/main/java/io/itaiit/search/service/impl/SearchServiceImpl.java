package io.itaiit.search.service.impl;

import io.itaiit.common.pojo.SearchItem;
import io.itaiit.common.pojo.SearchResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.search.dao.SearchDao;
import io.itaiit.search.mapper.SearchItemMapper;
import io.itaiit.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult importAllSearchItems() throws Exception {
        List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
        for (SearchItem item :
                searchItemList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId().toString());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            document.addField("item_desc", item.getItem_desc());
            solrClient.add("collection1", document);
        }
        solrClient.commit("collection1");
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        // 设置主查询条件
        if (StringUtils.isNoneBlank(queryString)) {
            solrQuery.setQuery(queryString);
        } else {
            solrQuery.setQuery("*:*");
        }
        // 设置过滤条件，设置分页
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 60;
        }
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        // 设置默认的搜索域
        solrQuery.set("df", "item_keywords");
        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        solrQuery.addHighlightField("item_title");
        SearchResult searach = searchDao.searach(solrQuery);
        long pageCount = 0, t = 0;
        pageCount = (long)Math.ceil(searach.getRecordCount() / (double)rows);
//        t = searach.getRecordCount() % rows;
//        if (t != 0) {
//            pageCount = searach.getRecordCount() / rows + 1;
//        } else {
//            pageCount = searach.getRecordCount() / rows;
//        }
        searach.setPageCount(pageCount);
        return searach;
    }
}
