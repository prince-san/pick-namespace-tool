import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    public void getIdealNodeScoreTest() {
        Node node = new Node(
                "namespace",
                30,
                1000,
                8,
                4
        );
        assertEquals(1.0, node.getNodeScore());
    }

    @Test
    public void getFullNodeScoreTest() {
        Node node = new Node(
                "namespace",
                30,
                600,
                8,
                4
        );
        for (int i = 0; i < 10; i++) {
            node.addRepo();
        }
        assertEquals(0.0, node.getNodeScore());
    }
}