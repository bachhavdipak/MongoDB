package connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import java.io.FileInputStream;
import java.util.Properties;

public class DbConnection {


    public static MongoClient connection(Properties properties) {

        MongoClient mongo = null;

        try {


            mongo = new MongoClient(properties.getProperty("db.host"), Integer.parseInt(properties.getProperty("db.port")));

            MongoCredential credential;
            credential = MongoCredential.createCredential(properties.getProperty("db.username"), properties.getProperty("db.name"),
                    properties.getProperty("db.password").toCharArray());
            System.out.println("Connected to the database successfully");


        } catch (Exception e) {

        }
        return mongo;
    }

}
