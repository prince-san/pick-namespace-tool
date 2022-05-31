import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Balancer {
    public static String pickNamespace(Context context) {
        if (context.getNodes().isEmpty()) throw new RuntimeException("Context doesn't contain any nodes");
        var a = Collections.max(context.getNodes()
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Node::getNamespace,
                                        Collectors.minBy(
                                                Comparator.comparingDouble(Node::getNodeScore)))).entrySet(),
                Comparator.comparingDouble(el -> el.getValue().orElseThrow().getNodeScore()));
        if (a.getValue().orElseThrow().getNodeScore() != 0.0) {
            return a.getKey();
        } else {
            throw new RuntimeException("All nodes are unable to host another repository");
        }
    }
}
