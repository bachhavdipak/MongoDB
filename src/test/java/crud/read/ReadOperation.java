package crud.read;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Properties;

public class ReadOperation {


    public static void readFirstElement(MongoClient mongo, Properties properties) {

        MongoDatabase database = mongo.getDatabase(properties.getProperty("db.name"));

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("sampleCollection");

        //Find the First Document in a Collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());

        //Find number of records
        System.out.println(collection.count());

    }

    public static void printAllData(MongoClient mongo, Properties properties) {
        MongoDatabase database = mongo.getDatabase(properties.getProperty("db.name"));

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("sampleCollection");

        //print all records
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }


    public static void titleSearchIndex(MongoClient mongo, Properties properties) {
        MongoDatabase database = mongo.getDatabase(properties.getProperty("db.name"));

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("sampleCollection");

        //get value from collection based on index title=5
        Document myDoc = collection.find().first();;
        collection.find(eq("title", 8)).first();
        System.out.println(myDoc.toJson());



    }


}
