package fileio;

import child.Child;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Writer {
    private final FileWriter file;
    private final JSONArray annualChildren = new JSONArray();

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * a
     * @param children b
     */
    @SuppressWarnings("unchecked")
    public void writeToFile(final ArrayList<Child> children) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonChildren = new JSONArray();
        ArrayList<Child> thisYearsChildren = (ArrayList<Child>)
                children.stream().map(Child::new).collect(Collectors.toList());
        jsonChildren.addAll(thisYearsChildren);
        jsonObject.put(Constants.CHILDREN, jsonChildren);
        annualChildren.add(jsonObject);
    }

    /**
     * writes to the file and close it
     *
     */
    @SuppressWarnings("unchecked")
    public void closeFile() throws IOException {
        JSONObject object = new JSONObject();
        object.put(Constants.ANNUAL_CHILDREN, annualChildren);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        writer.writeValue(file, object);
    }
}
