package nosqldbdesign;

import nosqldbdesign.basicApproach.DataCollection;
import nosqldbdesign.basicApproach.Document;
import nosqldbdesign.basicApproach.NoSqlDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDriver {
    public static void main(String[] args) {
        NoSqlDB db = new NoSqlDB();
        db.createDataCollection("users");

        DataCollection users = db.getDataCollection("users");
        users.addUniqueIndex("id");

        Map<String, Object> doc1 = new HashMap<>();
        doc1.put("id", 1);
        doc1.put("name", "Alice");

        users.insert(new Document(doc1));

        Map<String, Object> searchQuery = new HashMap<>();
        searchQuery.put("id", 1);

        List<Document> found = users.search(searchQuery);
        System.out.println("Found: " + found.size());
    }
}

