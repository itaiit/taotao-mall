package io.itaiit.search.listener;

import io.itaiit.common.pojo.SearchItem;
import io.itaiit.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class SearchIndexListener implements MessageListener {

    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrClient solrClient;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage)message;
            Long id = Long.parseLong(textMessage.getText());
            SearchItem item = searchItemMapper.getSearchItemById(id);
            // 更新索引库
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId().toString());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            document.addField("item_desc", item.getItem_desc());
            solrClient.add("collection1", document);
            solrClient.commit("collection1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
