// Actor classes 
abstract class Actor {
    protected String name;
    protected String id;
    
    public Actor(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() { return name; }
    public String getId() { return id; }
}

class Doctor extends Actor {
    private String specialty;
    
    public Doctor(String name, String id, String specialty) {
        super(name, id);
        this.specialty = specialty;
    }
    
    public void examinePatient(Patient patient) {
        System.out.println("Doctor " + name + " is examining patient " + patient.getName());
    }
    
    public void prescribeMedication(Patient patient, String medication) {
        System.out.println("Doctor " + name + " prescribed " + medication + " to " + patient.getName());
        updatePatientRecords(patient, "Prescribed: " + medication);
    }
    
    public void updatePatientRecords(Patient patient, String record) {
        patient.addMedicalRecord(record);
        System.out.println("Updated patient records for " + patient.getName());
    }
}

class Nurse extends Actor {
    public Nurse(String name, String id) {
        super(name, id);
    }
    
    public void viewMedicalHistory(Patient patient) {
        System.out.println("Nurse " + name + " viewing medical history of " + patient.getName());
        patient.displayMedicalHistory();
    }
}

class Patient extends Actor {
    private java.util.List<String> medicalHistory;
    private java.util.List<String> appointments;
    
    public Patient(String name, String id) {
        super(name, id);
        this.medicalHistory = new java.util.ArrayList<>();
        this.appointments = new java.util.ArrayList<>();
    }
    
    public void scheduleAppointment(String appointmentDetails) {
        appointments.add(appointmentDetails);
        System.out.println("Appointment scheduled for " + name + ": " + appointmentDetails);
    }
    
    public void addMedicalRecord(String record) {
        medicalHistory.add(record);
    }
    
    public void displayMedicalHistory() {
        System.out.println("Medical History for " + name + ":");
        for (String record : medicalHistory) {
            System.out.println("- " + record);
        }
    }
}

class Administrator extends Actor {
    public Administrator(String name, String id) {
        super(name, id);
    }
    
    public void generateMedicalReports(Patient patient) {
        System.out.println("Administrator " + name + " generating medical report for " + patient.getName());
        System.out.println("Report generated successfully");
    }
    
    public void registerPatient(String patientName, String patientId) {
        Patient newPatient = new Patient(patientName, patientId);
        System.out.println("Administrator " + name + " registered new patient: " + patientName);
    }
    
    public void processInsuranceClaim(Patient patient, double amount) {
        System.out.println("Processing insurance claim for " + patient.getName() + " - Amount: $" + amount);
        // This requires authentication
        if (authenticateUser()) {
            System.out.println("Insurance claim processed successfully");
        } else {
            System.out.println("Authentication failed - claim rejected");
        }
    }
    
    private boolean authenticateUser() {
        // Simple authentication simulation
        System.out.println("Authenticating user...");
        return true; // Simplified for demo
    }
}

// External system class 
class InsuranceSystem {
    public void connectToExternalSystem() {
        System.out.println("Connecting to external insurance system...");
    }
    
    public boolean processClaimRequest(String patientId, double amount) {
        System.out.println("External system processing claim for patient: " + patientId);
        return true;
    }
}

// Main Hospital Management System class 
public class UseCaseDiagramCode {
    private java.util.List<Doctor> doctors;
    private java.util.List<Nurse> nurses;
    private java.util.List<Patient> patients;
    private java.util.List<Administrator> administrators;
    private InsuranceSystem insuranceSystem;
    
    public UseCaseDiagramCode() {
        this.doctors = new java.util.ArrayList<>();
        this.nurses = new java.util.ArrayList<>();
        this.patients = new java.util.ArrayList<>();
        this.administrators = new java.util.ArrayList<>();
        this.insuranceSystem = new InsuranceSystem();
    }
    
    // Main method for testing 
    public static void main(String[] args) {
        UseCaseDiagramCode hospital = new UseCaseDiagramCode();
        
        // Create actors 
        Doctor doctor = new Doctor("Dr. Smith", "D001", "Cardiology");
        Nurse nurse = new Nurse("Nurse Johnson", "N001");
        Patient patient = new Patient("John Doe", "P001");
        Administrator admin = new Administrator("Admin Brown", "A001");
        
        // Add to system 
        hospital.doctors.add(doctor);
        hospital.nurses.add(nurse);
        hospital.patients.add(patient);
        hospital.administrators.add(admin);
        
        System.out.println("=== Hospital Management System Demo ===");
        System.out.println();
        
        // Demonstrate use cases 
        System.out.println("1. Register Patient:");
        admin.registerPatient("Jane Smith", "P002");
        System.out.println();
        
        System.out.println("2. Schedule Appointment:");
        patient.scheduleAppointment("Cardiology consultation - 2025-06-01 10:00 AM");
        System.out.println();
        
        System.out.println("3. Examine Patient:");
        doctor.examinePatient(patient);
        System.out.println();
        
        System.out.println("4. Prescribe Medication:");
        doctor.prescribeMedication(patient, "Aspirin 100mg daily");
        System.out.println();
        
        System.out.println("5. View Medical History:");
        nurse.viewMedicalHistory(patient);
        System.out.println();
        
        System.out.println("6. Generate Medical Reports:");
        admin.generateMedicalReports(patient);
        System.out.println();
        
        System.out.println("7. Process Insurance Claim:");
        admin.processInsuranceClaim(patient, 500.00);
        System.out.println();
        
        System.out.println("=== Demo Complete ===");
    }
}