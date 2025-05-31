import java.util.ArrayList;
import java.util.List;

// Interface Patient 
interface Patient {
    void registerPatient();
}

// PatientRecord class 
class PatientRecord implements Patient {
    private List<String> patientIds;
    private List<RecordEntry> patientRecords;
    
    public PatientRecord() {
        this.patientIds = new ArrayList<>();
        this.patientRecords = new ArrayList<>();
    }
    
    // Implementation of Patient interface
    @Override
    public void registerPatient() {
        System.out.println("Patient registered successfully");
    }
    
    // Getter and Setter for patientId
    public List<String> getPatientIds() {
        return patientIds;
    }
    
    public void addPatientId(String patientId) {
        this.patientIds.add(patientId);
        System.out.println("Added patient ID: " + patientId);
    }
    
    // Methods for managing patient records
    public void addPatientRecord(RecordEntry record) {
        this.patientRecords.add(record);
        System.out.println("Added record entry for patient");
    }
    
    public List<RecordEntry> getPatientRecords() {
        return patientRecords;
    }
    
    public void displayPatientInfo() {
        System.out.println("Patient Record Information:");
        System.out.println("Number of patients: " + patientIds.size());
        System.out.println("Number of records: " + patientRecords.size());
        
        for (int i = 0; i < patientIds.size(); i++) {
            System.out.println("Patient ID: " + patientIds.get(i));
        }
    }
}

// RecordEntry class
class RecordEntry {
    private String entryId;
    private String entryData;
    private String timestamp;
    
    public RecordEntry() {
        // Default constructor
    }
    
    public RecordEntry(String entryId, String entryData, String timestamp) {
        this.entryId = entryId;
        this.entryData = entryData;
        this.timestamp = timestamp;
    }
    
    // Getter and Setter methods
    public String getEntryId() {
        return entryId;
    }
    
    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }
    
    public String getEntryData() {
        return entryData;
    }
    
    public void setEntryData(String entryData) {
        this.entryData = entryData;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    // Method to display entry information
    public void displayEntry() {
        System.out.println("Entry ID: " + entryId);
        System.out.println("Entry Data: " + entryData);
        System.out.println("Timestamp: " + timestamp);
    }
    
    @Override
    public String toString() {
        return "RecordEntry{" +
                "entryId='" + entryId + '\'' +
                ", entryData='" + entryData + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

// Prescription class 
class Prescription {
    private String name;
    private List<String> prescriptions;
    
    public Prescription() {
        this.prescriptions = new ArrayList<>();
    }
    
    public Prescription(String name) {
        this.name = name;
        this.prescriptions = new ArrayList<>();
    }
    
    // Getter and Setter for name
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // Methods for managing prescriptions
    public void addPrescription(String prescription) {
        this.prescriptions.add(prescription);
        System.out.println("Added prescription: " + prescription);
    }
    
    public List<String> getPrescriptions() {
        return prescriptions;
    }
    
    public void displayPrescriptions() {
        System.out.println("Prescriptions for: " + name);
        for (String prescription : prescriptions) {
            System.out.println("- " + prescription);
        }
    }
    
    // Method to order in quantity (as shown in UML)
    public void orderInQuantity(int quantity, String medication) {
        String order = medication + " (Quantity: " + quantity + ")";
        addPrescription(order);
        System.out.println("Ordered " + quantity + " units of " + medication);
    }
}

// Main class for testing 
public class ClassDiagramCode {
    public static void main(String[] args) {
        System.out.println("=== Medical Records System Demo ===");
        System.out.println();
        
        // Create PatientRecord instance
        PatientRecord patientRecord = new PatientRecord();
        
        // Register patient
        System.out.println("1. Registering Patient:");
        patientRecord.registerPatient();
        patientRecord.addPatientId("P001");
        patientRecord.addPatientId("P002");
        System.out.println();
        
        // Create and add record entries
        System.out.println("2. Adding Record Entries:");
        RecordEntry entry1 = new RecordEntry("R001", "Initial consultation", "2025-05-31 09:00");
        RecordEntry entry2 = new RecordEntry("R002", "Blood test results", "2025-05-31 14:30");
        
        patientRecord.addPatientRecord(entry1);
        patientRecord.addPatientRecord(entry2);
        System.out.println();
        
        // Display record entries
        System.out.println("3. Displaying Record Entries:");
        entry1.displayEntry();
        System.out.println("---");
        entry2.displayEntry();
        System.out.println();
        
        // Create prescription
        System.out.println("4. Managing Prescriptions:");
        Prescription prescription = new Prescription("Dr. Smith");
        prescription.addPrescription("Aspirin 100mg - Take daily");
        prescription.addPrescription("Vitamin D 1000IU - Take weekly");
        prescription.orderInQuantity(30, "Aspirin 100mg tablets");
        System.out.println();
        
        // Display all prescriptions
        System.out.println("5. Displaying All Prescriptions:");
        prescription.displayPrescriptions();
        System.out.println();
        
        // Display patient information summary
        System.out.println("6. Patient Information Summary:");
        patientRecord.displayPatientInfo();
        System.out.println();
        
        // Display all record entries
        System.out.println("7. All Record Entries:");
        List<RecordEntry> allRecords = patientRecord.getPatientRecords();
        for (RecordEntry record : allRecords) {
            System.out.println(record.toString());
        }
        
        System.out.println();
        System.out.println("=== Demo Complete ===");
    }
}