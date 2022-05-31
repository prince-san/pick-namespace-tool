import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancerTest {
    @Test
    public void basicTest() {
        Context context = new Context(5);
        context.addNode(new Node(
                "namespace1",
                30,
                1000,
                8,
                4
        ));
        context.addNode(new Node(
                "namespace2",
                25,
                900,
                6,
                2
        ));
        assertEquals("namespace1", Balancer.pickNamespace(context));
    }

    @Test
    public void sameNamespaceCheckTest() {
        Context context = new Context(5);
        context.addNode(new Node(
                "Namespace1",
                30,
                1000,
                8,
                4
        ));
        context.addNode(new Node(
                "Namespace2",
                25,
                900,
                6,
                2
        ));
        context.addNode(new Node(
                "Namespace1",
                20,
                100,
                1,
                1
        ));
        assertEquals("Namespace2", Balancer.pickNamespace(context));
    }
}