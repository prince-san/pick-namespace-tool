import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class Context {
    private final int maxNodeAmount;

    private ArrayList<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        if (nodes.size() + 1 > maxNodeAmount) throw new IllegalArgumentException("Trying to add node to a full context");
        nodes.add(node);
    }
}
