package cn.sh.Elasticsearch.AdminYhk_Elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Hello world!
 *
 */
public class App {
	static TransportClient client = null;

	public static void main(String[] args) throws UnknownHostException {
		client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		// 添加索引
//		Map<String, Object> json = new HashMap<String, Object>();
//		json.put("user", "kimchy");
//		json.put("postDate", new Date());
//		json.put("message", "trying out Elastic Search");
//		IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(json).execute().actionGet();
		getIndex();
		client.close();
	}

	public void generateIndex() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("user", "kimchy");
		json.put("postDate", new Date());
		json.put("message", "trying out Elastic Search");
		IndexResponse response = this.client.prepareIndex("twitter", "tweet", "1").setSource(json).execute()
				.actionGet();
	}

	public static void getIndex() {
		GetResponse response = client.prepareGet("twitter", "tweet", "1").execute().actionGet();
		Map<String, Object> rpMap = response.getSource();
		if (rpMap == null) {
			System.out.println("empty");
			return;
		}
		Iterator<Entry<String, Object>> rpItor = rpMap.entrySet().iterator();
		while (rpItor.hasNext()) {
			Entry<String, Object> rpEnt = rpItor.next();
			System.out.println(rpEnt.getKey() + " : " + rpEnt.getValue());
		}
	}

}
