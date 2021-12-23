package observers;

import child.Child;
import fileio.Writer;

import java.util.ArrayList;

public class Output implements Observer {
    private final Writer writer;

    public Output(final Writer writer) {
        this.writer = writer;
    }

    /**
     * a
     * @param arg b
     */
    @SuppressWarnings("unchecked")
    @Override
    public void update(final Object arg) {
        ArrayList<Child> children = (ArrayList<Child>) arg;
        writer.writeToFile(children);
    }
}
