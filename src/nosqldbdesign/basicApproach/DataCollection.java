package nosqldbdesign.basicApproach;

import java.util.*;

public class DataCollection {
    private List<Document> documents = new ArrayList<>();
    private Set<String> uniqueIndexes = new HashSet<>();


    public void insert(Document doc) {
        for (String index : uniqueIndexes) {
            Object val = doc.getData().get(index);
            for (Document d : documents) {
                if (Objects.equals(d.getData().get(index), val)) {
                    throw new RuntimeException("Unique index violation on field: " + index);
                }
            }
        }
        documents.add(doc);
    }

    public void edit(int docId, String op, String key, Object value) {
        Document doc = documents.get(docId);
        switch (op) {
            case "add": doc.addKey(key, value); break;
            case "remove": doc.removeKey(key); break;
            case "update": doc.updateKey(key, value); break;
            default: throw new IllegalArgumentException("Invalid operation");
        }
    }

    public List<Document> search(Map<String, Object> query) {
        List<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.matches(query)) {
                results.add(doc);
            }
        }
        return results;
    }

    public void addUniqueIndex(String field) {
        uniqueIndexes.add(field);
    }

    public void deleteDocument(int docId) {
        documents.remove(docId);
    }
}
