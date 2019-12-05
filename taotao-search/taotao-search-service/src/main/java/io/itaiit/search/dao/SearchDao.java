package io.itaiit.search.dao;

import io.itaiit.common.pojo.SearchItem;
import io.itaiit.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 从索引库中搜索商品的dao
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrClient solrClient;
    public SearchResult searach(SolrQuery query) throws Exception {
        SearchResult searchResult = new SearchResult();
        QueryResponse response = solrClient.query("collection1", query);
        SolrDocumentList results = response.getResults();
        searchResult.setRecordCount(results.getNumFound());
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<SearchItem> items = new ArrayList<>();
        for (SolrDocument doc :
                results) {
            SearchItem item = new SearchItem();
            item.setId(Long.valueOf(doc.get("id").toString()));
            // 取高亮
            List<String> list = highlighting.get(doc.get("id").toString()).get("item_title");
            String gaoliangStr = "";
            if (list != null && list.size() > 0) {
                gaoliangStr = list.get(0);
            } else {
                gaoliangStr = doc.get("item_title").toString();
            }
            item.setTitle(gaoliangStr);
            item.setSell_point(doc.get("item_sell_point").toString());
            item.setPrice((Long)doc.get("item_price"));
            item.setImage(doc.get("item_image").toString());
            item.setCategory_name(doc.get("item_category_name").toString());
            items.add(item);
        }
        searchResult.setItemList(items);
        return searchResult;
    }
}
