package santa;

import child.Child;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Santa {
    private final double santaBudget;
    private final ArrayList<Child> children;
    private final ArrayList<Gift> gifts;

    public Santa(final double santaBudget, final ArrayList<Child> children,
                 final ArrayList<Gift> gifts) {
        this.santaBudget = santaBudget;
        this.children = children;
        this.gifts = gifts;
    }
}
