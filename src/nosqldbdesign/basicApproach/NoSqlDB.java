package nosqldbdesign.basicApproach;

import java.util.HashMap;
import java.util.Map;

public class NoSqlDB {
    private Map<String, DataCollection> DataCollections = new HashMap<>();

    public void createDataCollection(String name) {
        DataCollections.put(name, new DataCollection());
    }

    public void deleteDataCollection(String name) {
        DataCollections.remove(name);
    }

    public DataCollection getDataCollection(String name) {
        return DataCollections.get(name);
    }
}
