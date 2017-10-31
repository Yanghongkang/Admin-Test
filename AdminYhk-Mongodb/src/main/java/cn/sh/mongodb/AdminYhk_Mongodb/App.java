package cn.sh.mongodb.AdminYhk_Mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("AdminYhk");
		//mongoDatabase.createCollection("Test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("Test");
		Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100)
				.append("by", "Fly");
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
		System.out.println("文档插入成功");
	}
}
