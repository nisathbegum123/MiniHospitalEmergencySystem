import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientRecords = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentHistory = new TreatmentStack();
    private static int nextTreatmentId = 1;
    private static Patient patientInTreatment;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("     MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println("========================================");
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientRecords.displayInOrder();
                case 5 -> addToQueue();
                case 6 -> emergencyQueue.display();
                case 7 -> callNextPatient();
                case 8 -> completeTreatment();
                case 9 -> treatmentHistory.display();
                case 10 -> addVisit();
                case 11 -> removeVisit();
                case 12 -> searchVisit();
                case 13 -> displayVisits();
                case 14 -> running = false;
                default -> System.out.println("Invalid choice. Please select 1 to 14.");
            }
            if (running) pause();
        }
        System.out.println("Thank you for using the Mini Hospital Emergency System.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients (BST in-order)");
        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. View Emergency Queue");
        System.out.println("7. Call Next Patient for Treatment");
        System.out.println("8. Complete Treatment");
        System.out.println("9. View Treatment History");
        System.out.println("10. Add Patient Visit");
        System.out.println("11. Remove Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Display Patient Visit History");
        System.out.println("14. Exit");
    }

    private static void registerPatient() {
        int id = readPositiveInt("Patient ID: ");
        if (patientRecords.search(id) != null) {
            System.out.println("A patient with that ID already exists.");
            return;
        }
        String name = readRequired("Patient name: ");
        int age = readAge();
        String contact = readRequired("Contact number: ");
        String condition = readRequired("Medical condition: ");
        patientRecords.insert(new Patient(id, name, age, contact, condition));
        System.out.println("Patient registered successfully in the BST.");
    }

    private static void searchPatient() {
        Patient patient = patientRecords.search(readPositiveInt("Patient ID to search: "));
        if (patient == null) System.out.println("Patient not found.");
        else System.out.println(patient);
    }

    private static void deletePatient() {
        int id = readPositiveInt("Patient ID to delete: ");
        if (patientRecords.delete(id)) System.out.println("Patient deleted from the BST.");
        else System.out.println("Patient not found.");
    }

    private static void addToQueue() {
        Patient patient = findPatientByPrompt();
        if (patient == null) return;
        emergencyQueue.enqueue(patient);
        System.out.println("Patient added to the emergency queue.");
    }

    private static void callNextPatient() {
        if (patientInTreatment != null) {
            System.out.println("Complete the current treatment before calling another patient.");
            return;
        }
        patientInTreatment = emergencyQueue.dequeue();
        if (patientInTreatment == null) System.out.println("The emergency queue is empty.");
        else System.out.println("Now treating: " + patientInTreatment);
    }

    private static void completeTreatment() {
        if (patientInTreatment == null) {
            System.out.println("No patient is currently in treatment.");
            return;
        }
        String doctor = readRequired("Doctor name: ");
        String details = readRequired("Treatment/diagnosis: ");
        String date = readRequired("Date (for example, 2026-09-07): ");
        TreatmentRecord record = new TreatmentRecord(nextTreatmentId++, patientInTreatment.getPatientId(),
                patientInTreatment.getPatientName(), doctor, details, date);
        treatmentHistory.push(record);
        System.out.println("Treatment completed and pushed onto the stack.");
        patientInTreatment = null;
    }

    private static Patient findPatientByPrompt() {
        Patient patient = patientRecords.search(readPositiveInt("Patient ID: "));
        if (patient == null) System.out.println("Patient not found.");
        return patient;
    }

    private static void addVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) return;
        int visitId = readPositiveInt("Visit ID: ");
        String date = readRequired("Visit date: ");
        String doctor = readRequired("Doctor name: ");
        String diagnosis = readRequired("Diagnosis: ");
        String treatment = readRequired("Treatment: ");
        if (patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment)))
            System.out.println("Visit added to the patient's linked list.");
        else System.out.println("A visit with that ID already exists.");
    }

    private static void removeVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) return;
        if (patient.getVisitHistory().removeVisit(readPositiveInt("Visit ID to remove: ")))
            System.out.println("Visit removed from the linked list.");
        else System.out.println("Visit not found.");
    }

    private static void searchVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) return;
        Visit visit = patient.getVisitHistory().searchVisit(readPositiveInt("Visit ID to search: "));
        if (visit == null) System.out.println("Visit not found.");
        else System.out.println(visit);
    }

    private static void displayVisits() {
        Patient patient = findPatientByPrompt();
        if (patient != null) patient.getVisitHistory().display();
    }

    private static int readAge() {
        while (true) {
            int age = readInt("Age (1-130): ");
            if (age >= 1 && age <= 130) return age;
            System.out.println("Age must be between 1 and 130.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) return value;
            System.out.println("Please enter a positive whole number.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    private static String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("This field cannot be empty.");
        }
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
