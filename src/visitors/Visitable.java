package visitors;

public interface Visitable {
    /**
     *
     * @param visitor a
     */
    void accept(Visitor visitor);
}
