import java.util.ArrayList;
import java.util.List;

// HIS (Hospital Information System) class - 医院信息系统类
class HIS {
    private String systemName;
    private boolean isActive;
    
    public HIS() {
        this.systemName = "Hospital Information System";
        this.isActive = true;
    }
    
    public void printOrder() {
        System.out.println("HIS: Printing order request initiated");
        // This method initiates the order printing process
    }
    
    public void makeOrder() {
        System.out.println("HIS: Making order request initiated");
        // This method initiates the order making process
    }
    
    public String getSystemName() {
        return systemName;
    }
    
    public boolean isActive() {
        return isActive;
    }
}

// Ordering system class - 订购系统类
class Ordering {
    private String orderingSystemId;
    private List<String> orders;
    
    public Ordering() {
        this.orderingSystemId = "ORD-SYS-001";
        this.orders = new ArrayList<>();
    }
    
    public void printOrder() {
        System.out.println("Ordering System: Processing print order request");
        // Process the print order
    }
    
    public void makeOrder() {
        System.out.println("Ordering System: Processing make order request");
        
        // Create and send message
        Message message = new Message("Order creation request", "ORDER_CREATE");
        System.out.println("Ordering System: Sending message - " + message.getContent());
        
        // Process message and create response
        Message response = processMessage(message);
        System.out.println("Ordering System: Received response - " + response.getContent());
        
        // Complete the order making process
        completeOrderProcess();
    }
    
    private Message processMessage(Message message) {
        // Simulate message processing
        System.out.println("Ordering System: Processing message internally");
        return new Message("Order processed successfully", "ORDER_RESPONSE");
    }
    
    private void completeOrderProcess() {
        String orderId = "ORD-" + System.currentTimeMillis();
        orders.add(orderId);
        System.out.println("Ordering System: Order completed with ID: " + orderId);
    }
    
    public List<String> getOrders() {
        return orders;
    }
    
    public String getOrderingSystemId() {
        return orderingSystemId;
    }
}

// Message class - 消息类
class Message {
    private String content;
    private String messageType;
    private long timestamp;
    private String messageId;
    
    public Message(String content, String messageType) {
        this.content = content;
        this.messageType = messageType;
        this.timestamp = System.currentTimeMillis();
        this.messageId = "MSG-" + timestamp;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getMessageType() {
        return messageType;
    }
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public String getMessageId() {
        return messageId;
    }
    
    @Override
    public String toString() {
        return "Message{" +
                "id='" + messageId + '\'' +
                ", content='" + content + '\'' +
                ", type='" + messageType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

// Alternative flow handler - 可选流程处理器
class AlternativeFlow {
    private boolean condition;
    
    public AlternativeFlow() {
        this.condition = false;
    }
    
    public void checkCondition() {
        // Simulate condition checking
        this.condition = Math.random() > 0.5; // Random condition for demo
        System.out.println("Alternative Flow: Condition check result = " + condition);
    }
    
    public void executeAlternativeFlow() {
        if (condition) {
            System.out.println("Alternative Flow: Executing alternative process");
        } else {
            System.out.println("Alternative Flow: Continuing with normal process");
        }
    }
    
    public boolean isCondition() {
        return condition;
    }
}

// Reference interaction handler - 引用交互处理器
class ReferenceInteraction {
    private String interactionName;
    
    public ReferenceInteraction(String interactionName) {
        this.interactionName = interactionName;
    }
    
    public void executeSubInteraction() {
        System.out.println("Reference Interaction: Executing sub-interaction - " + interactionName);
        // Simulate sub-interaction execution
        simulateSubProcess();
    }
    
    private void simulateSubProcess() {
        System.out.println("Reference Interaction: Sub-process started");
        System.out.println("Reference Interaction: Sub-process completed");
    }
    
    public String getInteractionName() {
        return interactionName;
    }
}

// Main sequence diagram system class - 主序列图系统类
public class SequenceDiagramCodeFromGenerated {
    private HIS his;
    private Ordering ordering;
    private AlternativeFlow alternativeFlow;
    private ReferenceInteraction referenceInteraction;
    
    public SequenceDiagramCodeFromGenerated() {
        this.his = new HIS();
        this.ordering = new Ordering();
        this.alternativeFlow = new AlternativeFlow();
        this.referenceInteraction = new ReferenceInteraction("OrderProcessing");
    }
    
    // Simulate the first sequence diagram flow
    public void executePrintOrderSequence() {
        System.out.println("=== Print Order Sequence ===");
        
        // HIS initiates print order
        his.printOrder();
        
        // Ordering system processes the request
        ordering.printOrder();
        
        System.out.println("Print order sequence completed\n");
    }
    
    // Simulate the second sequence diagram flow
    public void executeMakeOrderSequence() {
        System.out.println("=== Make Order Sequence ===");
        
        // HIS initiates make order
        his.makeOrder();
        
        // Ordering system processes the request with messages
        ordering.makeOrder();
        
        // Check for alternative flows
        alternativeFlow.checkCondition();
        alternativeFlow.executeAlternativeFlow();
        
        // Execute reference interaction if needed
        referenceInteraction.executeSubInteraction();
        
        System.out.println("Make order sequence completed\n");
    }
    
    // Display system status
    public void displaySystemStatus() {
        System.out.println("=== System Status ===");
        System.out.println("HIS System: " + his.getSystemName() + " - Active: " + his.isActive());
        System.out.println("Ordering System ID: " + ordering.getOrderingSystemId());
        System.out.println("Total Orders: " + ordering.getOrders().size());
        
        if (!ordering.getOrders().isEmpty()) {
            System.out.println("Recent Orders:");
            for (String orderId : ordering.getOrders()) {
                System.out.println("  - " + orderId);
            }
        }
        System.out.println();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Sequence Diagram System Demo ===");
        System.out.println();
        
        SequenceDiagramCodeFromGenerated system = new SequenceDiagramCodeFromGenerated();
        
        // Execute different sequence flows
        system.executePrintOrderSequence();
        
        system.executeMakeOrderSequence();
        
        // Execute another make order to show multiple orders
        system.executeMakeOrderSequence();
        
        // Display final system status
        system.displaySystemStatus();
        
        // Demonstrate message creation separately
        System.out.println("=== Message Demonstration ===");
        Message testMessage = new Message("Test message content", "TEST_TYPE");
        System.out.println("Created message: " + testMessage.toString());
        
        System.out.println("\n=== Demo Complete ===");
    }
}