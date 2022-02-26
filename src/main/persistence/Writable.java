package persistence;

import org.json.JSONObject;

// Modelled after Writable interface in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
