public class PatientNode {
    private Patient patient;
    private PatientNode left;
    private PatientNode right;

    public PatientNode(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public PatientNode getLeft() { return left; }
    public void setLeft(PatientNode left) { this.left = left; }
    public PatientNode getRight() { return right; }
    public void setRight(PatientNode right) { this.right = right; }
}
