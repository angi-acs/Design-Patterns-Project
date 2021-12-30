package visitors;

public interface Visitable {
    /**
     * @param visitor to be accepted
     */
    void accept(Visitor visitor);
}
