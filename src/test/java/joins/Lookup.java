package joins;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import static com.mongodb.client.model.Aggregates.lookup;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Lookup {
/* public static void insertOperation(MongoClient mongo, Properties properties) {
        MongoDatabase database = mongo.getDatabase(properties.getProperty("db.name"));

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("post");

        System.out.println("Collection sampleCollection selected successfully");

        Document document = new Document("title", "my first post")
                .append("author", "Jim")
                .append("likes", 5);
        collection.insertOne(document);

        Document document0 = new Document("title", "my second post")
                .append("author", "Jim")
                .append("likes", 2);
        collection.insertOne(document0);

        Document document1 = new Document("title", "hello world")
                .append("author", "Joe")
                .append("likes", 3);
        collection.insertOne(document1);

        System.out.println("Document inserted successfully to post collection");



        MongoCollection<Document> collection0 = database.getCollection("comment ");

        System.out.println("Collection sampleCollection selected successfully");

        Document doc = new Document("postTitle", "my first post")
                .append("comment", "great read")
                .append("likes", 3);
        collection0.insertOne(doc);

        Document doc0 = new Document("postTitle", "my second post")
                .append("comment", "good info")
                .append("likes", 0);
        collection0.insertOne(doc0);

        Document doc1 = new Document("postTitle", "my second post")
                .append("comment", "i liked this post")
                .append("likes", 12);
        collection0.insertOne(doc1);


        Document doc2 = new Document("postTitle", "hello world")
                .append("comment", "not my favorite")
                .append("likes", 8);
        collection0.insertOne(doc2);


        Document doc3 = new Document("postTitle", "my last post")
                .append("comment", null)
                .append("likes", 0);
        collection0.insertOne(doc3);

        System.out.println("Document inserted successfully to comment collection");





    }
    public MongoAggregation lookup(String from, String localField, String foreignField, String as) {
        pipeline.add(Aggregates.lookup(from, localField, foreignField, as));
        return this;
    }*/


    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> books = db.getCollection("books");
        MongoCollection<Document> authors = db.getCollection("authors");
        books.drop();
        authors.drop();
        books.insertOne(new Document("_id", 1).append("title", "Super Book").append("authors", asList(1, 2)));
        authors.insertOne(new Document("_id", 1).append("name", "Bob"));
        authors.insertOne(new Document("_id", 2).append("name", "Alice"));

        Bson pipeline = lookup("authors", "authors", "_id", "authors");
        List<Document> booksJoined = books.aggregate(singletonList(pipeline)).into(new ArrayList<>());
        booksJoined.forEach(printDocuments());
    }

        private static Consumer<Document> printDocuments () {
            return doc -> System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
        }

    }
