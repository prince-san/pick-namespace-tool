import lombok.Getter;
import lombok.Setter;

@Getter
public class Node {
    private final String namespace;
    @Setter
    private int repositoryAmount = 0;

    private final int maxRepositorySize;

    private final int diskSize;

    private final int memorySize;

    private final int cpuAmount;


    public Node(String namespace, int maxRepositorySize, int diskSize, int memorySize, int cpuAmount) {
        this.namespace = namespace;
        this.maxRepositorySize = Util.inRange(maxRepositorySize, NodeConfig.MIN_REPOSITORY_SIZE, NodeConfig.MAX_REPOSITORY_SIZE);
        this.diskSize = Util.inRange(diskSize, NodeConfig.MIN_DISK_SIZE, NodeConfig.MAX_DISK_SIZE);
        this.memorySize = Util.inRange(memorySize, NodeConfig.MIN_MEMORY_SIZE, NodeConfig.MAX_MEMORY_SIZE);
        this.cpuAmount = Util.inRange(cpuAmount, NodeConfig.MIN_CPU_AMOUNT, NodeConfig.MAX_CPU_AMOUNT);
    }

    public boolean canAddRepo() {
        return 2 * repositoryAmount * maxRepositorySize < diskSize;
    }

    public void addRepo() {
        if (canAddRepo()) {
            repositoryAmount++;
        } else {
            throw new RuntimeException("Repository is already full!");
        }
    }

    public double getNodeScore() {
        return (0.25 * (diskSize - (2 * repositoryAmount * maxRepositorySize)) / diskSize +
                0.25 * (memorySize - NodeConfig.MIN_MEMORY_SIZE) / (NodeConfig.MAX_MEMORY_SIZE - NodeConfig.MIN_MEMORY_SIZE) / (repositoryAmount + 1) +
                0.25 * (cpuAmount - NodeConfig.MIN_CPU_AMOUNT) / (NodeConfig.MAX_CPU_AMOUNT - NodeConfig.MIN_CPU_AMOUNT) / (repositoryAmount + 1) +
                0.25 * (maxRepositorySize - NodeConfig.MIN_REPOSITORY_SIZE) / (NodeConfig.MAX_REPOSITORY_SIZE - NodeConfig.MIN_REPOSITORY_SIZE)) *
                (canAddRepo() ? 1 : 0);
    }
}

class NodeConfig {
    final static int MAX_REPOSITORY_SIZE = 30;
    final static int MIN_REPOSITORY_SIZE = 10;
    final static int MAX_DISK_SIZE = 1000;
    final static int MIN_DISK_SIZE = 100;
    final static int MAX_MEMORY_SIZE = 8;
    final static int MIN_MEMORY_SIZE = 1;
    final static int MAX_CPU_AMOUNT = 4;
    final static int MIN_CPU_AMOUNT = 1;
}