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
     * Calls the writer's method
     * @param arg cast as a list of children
     */
    @Override
    public void update(final Object arg) {
        ArrayList<Child> children = (ArrayList<Child>) arg;
        writer.writeToFile(children);
    }
}
