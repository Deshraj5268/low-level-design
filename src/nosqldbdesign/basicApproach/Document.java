package nosqldbdesign.basicApproach;

import java.util.Map;

public class Document {

    private Map<String, Object> data;

    public Document(Map<String,Object> data){
        this.data = data;
    }

    public Map<String,Object> getData(){
        return data;
    }

    public void addKey(String key, Object value){
        data.put(key,value);
    }
    public void removeKey(String key) {
        data.remove(key);
    }

    public void updateKey(String key, Object value) {
        data.put(key, value);
    }

    public boolean matches(Map<String, Object> query) {
        for (String key : query.keySet()) {
            Object qVal = query.get(key);
            Object dVal = data.get(key);
            if (dVal == null || !dVal.equals(qVal)) return false;
        }
        return true;
    }

}
