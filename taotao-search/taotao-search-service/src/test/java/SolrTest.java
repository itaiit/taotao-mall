import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolrTest {

    @Test
    public void solrInit() throws IOException, SolrServerException {
        HttpSolrClient client = new HttpSolrClient.Builder("http://192.168.61.136:8080/solr")
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
// solr 查询
//        final Map<String, String> queryParamMap = new HashMap<String, String>();
//        queryParamMap.put("q", "*:*");
//        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
//        QueryResponse response = client.query("collection1", queryParams);
//        System.out.println(response.getResults().getNumFound());
// solr 插入1个
        List<SolrInputDocument> documents = new ArrayList<>();

//        SolrInputDocument document = new SolrInputDocument();
//        document.addField("item_title", "apple");
//        SolrInputDocument document2 = new SolrInputDocument();
//        document2.addField("item_title", "banana");
//        documents.add(document);
//        documents.add(document2);
//        client.add("collection1",documents);
//        client.commit("collection1");
    }
}
