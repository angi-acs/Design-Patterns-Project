package observers;

public interface Observer {
    /**
     * @param arg object necessary for implementation;
     *            it will be cast as needed in method's body
     */
    void update(Object arg);
}
