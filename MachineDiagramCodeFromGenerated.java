// State enumeration - 状态枚举
enum BathtubState {
    INITIAL,
    EMPTY,
    FILLED,
    FULL,
    FINAL
}

// Event enumeration - 事件枚举
enum BathtubEvent {
    F2E,    // Fill to Empty (x == 0)
    E2F,    // Empty to Fill (fill(x <= self->Lm - self->l))
    F2R,    // Fill to Refill (withdraw(self->l-x > 0))
    F2FF,   // Fill to Full Fill
    FINISH  // To final state
}

// Bathtub State Machine class - 浴缸状态机类
public class MachineDiagramCodeFromGenerated {
    private BathtubState currentState;
    private int waterLevel;      // Current water level (l)
    private int maxCapacity;     // Maximum capacity (Lm)
    private int x;               // Operation parameter
    
    // Constructor - 构造函数
    public MachineDiagramCodeFromGenerated(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.waterLevel = 0;
        this.x = 0;
        this.currentState = BathtubState.INITIAL;
        System.out.println("Bathtub State Machine initialized with max capacity: " + maxCapacity);
    }
    
    // Initialize the state machine - 初始化状态机
    public void initialize() {
        currentState = BathtubState.EMPTY;
        waterLevel = 0;
        System.out.println("State Machine initialized. Current state: " + currentState);
        entryOpaqueBehavior();
    }
    
    // Handle events - 处理事件
    public void handleEvent(BathtubEvent event, int parameter) {
        this.x = parameter;
        System.out.println("\nHandling event: " + event + " with parameter: " + parameter);
        System.out.println("Current state: " + currentState + ", Water level: " + waterLevel);
        
        switch (currentState) {
            case EMPTY:
                handleEmptyState(event);
                break;
            case FILLED:
                handleFilledState(event);
                break;
            case FULL:
                handleFullState(event);
                break;
            default:
                System.out.println("No transitions available from state: " + currentState);
                break;
        }
    }
    
    // Handle events in Empty state - 处理空状态的事件
    private void handleEmptyState(BathtubEvent event) {
        switch (event) {
            case E2F:
                if (x <= maxCapacity - waterLevel) {
                    exitOpaqueBehavior();
                    transitionToFilled();
                } else {
                    System.out.println("Cannot fill: exceeds capacity limit");
                }
                break;
            case FINISH:
                transitionToFinal();
                break;
            default:
                System.out.println("Invalid event " + event + " for Empty state");
                break;
        }
    }
    
    // Handle events in Filled state - 处理已填充状态的事件
    private void handleFilledState(BathtubEvent event) {
        switch (event) {
            case F2E:
                if (x == 0) {
                    exitOpaqueBehavior();
                    transitionToEmpty();
                }
                break;
            case F2R:
                if (waterLevel - x > 0) {
                    withdraw();
                    // Stay in filled state after withdrawal
                    System.out.println("Withdrawn " + x + " units. Remaining in Filled state.");
                }
                break;
            case F2FF:
                if (waterLevel == maxCapacity) {
                    exitOpaqueBehavior();
                    transitionToFull();
                }
                break;
            case E2F:
                if (x <= maxCapacity - waterLevel) {
                    fill();
                }
                break;
            case FINISH:
                transitionToFinal();
                break;
            default:
                System.out.println("Invalid event " + event + " for Filled state");
                break;
        }
    }
    
    // Handle events in Full state - 处理满状态的事件
    private void handleFullState(BathtubEvent event) {
        switch (event) {
            case F2FF:
                if (waterLevel == maxCapacity) {
                    // Self-transition in Full state
                    doActivityFull();
                }
                break;
            case F2R:
                if (waterLevel - x > 0) {
                    exitOpaqueBehavior();
                    transitionToFilled();
                    withdraw();
                }
                break;
            case FINISH:
                transitionToFinal();
                break;
            default:
                System.out.println("Invalid event " + event + " for Full state");
                break;
        }
    }
    
    // State transition methods - 状态转换方法
    private void transitionToEmpty() {
        currentState = BathtubState.EMPTY;
        waterLevel = 0;
        System.out.println("Transitioned to Empty state");
        entryOpaqueBehavior();
    }
    
    private void transitionToFilled() {
        currentState = BathtubState.FILLED;
        System.out.println("Transitioned to Filled state");
        doActivityFilled();
        entryOpaqueBehavior();
    }
    
    private void transitionToFull() {
        currentState = BathtubState.FULL;
        System.out.println("Transitioned to Full state");
        doActivityFull();
        entryOpaqueBehavior();
    }
    
    private void transitionToFinal() {
        currentState = BathtubState.FINAL;
        System.out.println("Transitioned to Final state - State machine terminated");
    }
    
    // Action methods - 动作方法
    private void fill() {
        waterLevel += x;
        if (waterLevel > maxCapacity) {
            waterLevel = maxCapacity;
        }
        System.out.println("Filled " + x + " units. Current water level: " + waterLevel);
        
        // Check if transition to full is needed
        if (waterLevel == maxCapacity) {
            handleEvent(BathtubEvent.F2FF, 0);
        }
    }
    
    private void withdraw() {
        waterLevel -= x;
        if (waterLevel < 0) {
            waterLevel = 0;
        }
        System.out.println("Withdrew " + x + " units. Current water level: " + waterLevel);
        
        // Check if transition to empty is needed
        if (waterLevel == 0) {
            handleEvent(BathtubEvent.F2E, 0);
        }
    }
    
    // Entry/Exit/Do behaviors - 进入/退出/执行行为
    private void entryOpaqueBehavior() {
        System.out.println("Entry behavior executed for state: " + currentState);
    }
    
    private void exitOpaqueBehavior() {
        System.out.println("Exit behavior executed for state: " + currentState);
    }
    
    private void doActivityFilled() {
        System.out.println("Do activity: Filled state active");
    }
    
    private void doActivityFull() {
        System.out.println("Do activity: Full state active - bathtub is at maximum capacity");
    }
    
    // Getter methods - 获取方法
    public BathtubState getCurrentState() {
        return currentState;
    }
    
    public int getWaterLevel() {
        return waterLevel;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    // Display current status - 显示当前状态
    public void displayStatus() {
        System.out.println("\n=== Bathtub Status ===");
        System.out.println("Current State: " + currentState);
        System.out.println("Water Level: " + waterLevel + "/" + maxCapacity);
        System.out.println("Capacity: " + (waterLevel * 100 / maxCapacity) + "%");
        System.out.println("=====================\n");
    }
    
    // Main method for testing - 主测试方法
    public static void main(String[] args) {
        System.out.println("=== Bathtub State Machine Demo ===\n");
        
        // Create bathtub with capacity of 100 units
        MachineDiagramCodeFromGenerated bathtub = new MachineDiagramCodeFromGenerated(100);
        
        // Initialize the state machine
        bathtub.initialize();
        bathtub.displayStatus();
        
        // Scenario 1: Fill the bathtub partially
        System.out.println("--- Scenario 1: Partial Fill ---");
        bathtub.handleEvent(BathtubEvent.E2F, 30);
        bathtub.displayStatus();
        
        // Scenario 2: Fill more water
        System.out.println("--- Scenario 2: Fill More ---");
        bathtub.handleEvent(BathtubEvent.E2F, 50);
        bathtub.displayStatus();
        
        // Scenario 3: Fill to maximum capacity
        System.out.println("--- Scenario 3: Fill to Full ---");
        bathtub.handleEvent(BathtubEvent.E2F, 20);
        bathtub.displayStatus();
        
        // Scenario 4: Withdraw some water
        System.out.println("--- Scenario 4: Withdraw Water ---");
        bathtub.handleEvent(BathtubEvent.F2R, 30);
        bathtub.displayStatus();
        
        // Scenario 5: Empty the bathtub
        System.out.println("--- Scenario 5: Empty Bathtub ---");
        bathtub.handleEvent(BathtubEvent.F2R, 70);
        bathtub.displayStatus();
        
        // Scenario 6: Finish and terminate
        System.out.println("--- Scenario 6: Terminate ---");
        bathtub.handleEvent(BathtubEvent.FINISH, 0);
        bathtub.displayStatus();
        
        System.out.println("=== Demo Complete ===");
    }
}