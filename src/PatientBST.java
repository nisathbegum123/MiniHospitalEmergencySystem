public class PatientBST {
    private PatientNode root;

    public boolean insert(Patient patient) {
        if (patient == null) return false;
        if (root == null) {
            root = new PatientNode(patient);
            return true;
        }
        return insert(root, patient);
    }

    private boolean insert(PatientNode current, Patient patient) {
        if (patient.getPatientId() == current.getPatient().getPatientId()) return false;
        if (patient.getPatientId() < current.getPatient().getPatientId()) {
            if (current.getLeft() == null) {
                current.setLeft(new PatientNode(patient));
                return true;
            }
            return insert(current.getLeft(), patient);
        }
        if (current.getRight() == null) {
            current.setRight(new PatientNode(patient));
            return true;
        }
        return insert(current.getRight(), patient);
    }

    public Patient search(int patientId) {
        PatientNode current = root;
        while (current != null) {
            int currentId = current.getPatient().getPatientId();
            if (patientId == currentId) return current.getPatient();
            current = patientId < currentId ? current.getLeft() : current.getRight();
        }
        return null;
    }

    public boolean delete(int patientId) {
        if (search(patientId) == null) return false;
        root = delete(root, patientId);
        return true;
    }

    // Deletion handles leaf, one-child, and two-child nodes.
    private PatientNode delete(PatientNode current, int patientId) {
        if (current == null) return null;
        if (patientId < current.getPatient().getPatientId()) {
            current.setLeft(delete(current.getLeft(), patientId));
        } else if (patientId > current.getPatient().getPatientId()) {
            current.setRight(delete(current.getRight(), patientId));
        } else {
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();
            PatientNode successor = findSmallest(current.getRight());
            current.setPatient(successor.getPatient());
            current.setRight(delete(current.getRight(), successor.getPatient().getPatientId()));
        }
        return current;
    }

    private PatientNode findSmallest(PatientNode node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        displayInOrder(root);
    }

    private void displayInOrder(PatientNode node) {
        if (node == null) return;
        displayInOrder(node.getLeft());
        System.out.println(node.getPatient());
        displayInOrder(node.getRight());
    }
}
