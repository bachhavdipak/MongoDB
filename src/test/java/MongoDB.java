import com.mongodb.MongoClient;
import crud.create.CreateOperation;
import crud.read.ReadOperation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MongoDB {

    public static void main(String[] args) throws IOException {

        Properties properties = null;
        properties = new Properties();
        properties.load(new FileInputStream("database.properties"));

        MongoClient mongo=connection.DbConnection.connection(properties);

    /*    //Insert Data
        CreateOperation.insertOperation(mongo,properties);

       //Read Data
        ReadOperation.readFirstElement(mongo,properties);
        ReadOperation.printAllElements(mongo,properties);*/
       ReadOperation.titleSearchIndex(mongo,properties);






    }
}

