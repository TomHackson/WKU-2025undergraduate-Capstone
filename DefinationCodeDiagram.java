// Product interfaces - 产品接口
interface ProductA {
    void useA();
}

interface ProductB {
    void useB();
}

// Concrete Product implementations - 具体产品实现
class ProductAX implements ProductA {
    private String attributes;
    
    public ProductAX() {
        this.attributes = "ProductAX attributes";
    }
    
    @Override
    public void useA() {
        System.out.println("Using ProductAX with attributes: " + attributes);
    }
    
    public String getAttributes() {
        return attributes;
    }
    
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}

class ProductBX implements ProductB {
    private String attributes;
    
    public ProductBX() {
        this.attributes = "ProductBX attributes";
    }
    
    @Override
    public void useB() {
        System.out.println("Using ProductBX with attributes: " + attributes);
    }
    
    public String getAttributes() {
        return attributes;
    }
    
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}

// Abstract Factory interface - 抽象工厂接口
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

// Concrete Factory implementation - 具体工厂实现
class ConcreteFactoryX implements AbstractFactory {
    private String attributes;
    
    public ConcreteFactoryX() {
        this.attributes = "ConcreteFactoryX attributes";
    }
    
    @Override
    public ProductA createProductA() {
        System.out.println("ConcreteFactoryX creating ProductA");
        return new ProductAX();
    }
    
    @Override
    public ProductB createProductB() {
        System.out.println("ConcreteFactoryX creating ProductB");
        return new ProductBX();
    }
    
    public String getAttributes() {
        return attributes;
    }
    
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}

// Client class - 客户端类
class Client {
    private AbstractFactory factory;
    private ProductA productA;
    private ProductB productB;
    
    public Client(AbstractFactory factory) {
        this.factory = factory;
    }
    
    public void create() {
        System.out.println("Client creating products using factory...");
        this.productA = factory.createProductA();
        this.productB = factory.createProductB();
    }
    
    public void useA() {
        if (productA != null) {
            productA.useA();
        } else {
            System.out.println("ProductA not created yet. Call create() first.");
        }
    }
    
    public void useB() {
        if (productB != null) {
            productB.useB();
        } else {
            System.out.println("ProductB not created yet. Call create() first.");
        }
    }
    
    public AbstractFactory getFactory() {
        return factory;
    }
    
    public void setFactory(AbstractFactory factory) {
        this.factory = factory;
    }
    
    public ProductA getProductA() {
        return productA;
    }
    
    public ProductB getProductB() {
        return productB;
    }
}

// Additional operations class - 附加操作类
class Operations {
    public void create() {
        System.out.println("Operations: Creating resources...");
    }
    
    public void useA() {
        System.out.println("Operations: Using type A resources...");
    }
    
    public void useB() {
        System.out.println("Operations: Using type B resources...");
    }
}

// Main class for testing the Abstract Factory Pattern - 主测试类
public class DefinationCodeDiagram {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===");
        System.out.println();
        
        // Create operations instance
        Operations operations = new Operations();
        
        // Step 1: Create concrete factory
        System.out.println("1. Creating ConcreteFactoryX:");
        ConcreteFactoryX factory = new ConcreteFactoryX();
        System.out.println("Factory created with attributes: " + factory.getAttributes());
        System.out.println();
        
        // Step 2: Create client with factory
        System.out.println("2. Creating Client with factory:");
        Client client = new Client(factory);
        System.out.println("Client created successfully");
        System.out.println();
        
        // Step 3: Use operations to create
        System.out.println("3. Using Operations:");
        operations.create();
        operations.useA();
        operations.useB();
        System.out.println();
        
        // Step 4: Client creates products
        System.out.println("4. Client creating products:");
        client.create();
        System.out.println();
        
        // Step 5: Client uses products
        System.out.println("5. Client using products:");
        client.useA();
        client.useB();
        System.out.println();
        
        // Step 6: Direct factory usage
        System.out.println("6. Direct factory usage:");
        ProductA directProductA = factory.createProductA();
        ProductB directProductB = factory.createProductB();
        
        directProductA.useA();
        directProductB.useB();
        System.out.println();
        
        // Step 7: Show product attributes
        System.out.println("7. Product information:");
        if (directProductA instanceof ProductAX) {
            ProductAX productAX = (ProductAX) directProductA;
            System.out.println("ProductAX attributes: " + productAX.getAttributes());
        }
        
        if (directProductB instanceof ProductBX) {
            ProductBX productBX = (ProductBX) directProductB;
            System.out.println("ProductBX attributes: " + productBX.getAttributes());
        }
        System.out.println();
        
        // Step 8: Demonstrate pattern benefits
        System.out.println("8. Pattern Benefits Demonstration:");
        demonstratePatternBenefits(client);
        
        System.out.println("=== Demo Complete ===");
    }
    
    private static void demonstratePatternBenefits(Client client) {
        System.out.println("- Client works with abstract interfaces");
        System.out.println("- Products are created through factory abstraction");
        System.out.println("- Easy to switch between different factory implementations");
        System.out.println("- Products from same factory family work together");
        
        // Show that client doesn't need to know concrete types
        AbstractFactory abstractFactory = client.getFactory();
        ProductA product = abstractFactory.createProductA();
        product.useA();
    }
}