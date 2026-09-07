public class Patient {
    private final int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private final VisitHistory visitHistory;

    public Patient(int patientId, String patientName, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getMedicalCondition() { return medicalCondition; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }
    public VisitHistory getVisitHistory() { return visitHistory; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Contact: %s | Condition: %s",
                patientId, patientName, age, contactNumber, medicalCondition);
    }
}
