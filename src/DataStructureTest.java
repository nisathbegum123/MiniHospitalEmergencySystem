public class DataStructureTest {
    public static void main(String[] args) {
        PatientBST bst = new PatientBST();
        Patient first = new Patient(105, "Amina Noor", 25, "0700000001", "Asthma");
        Patient second = new Patient(101, "Brian Otieno", 41, "0700000002", "Fever");
        Patient third = new Patient(110, "Chloe Mensah", 33, "0700000003", "Fracture");
        Patient fourth = new Patient(103, "David Kim", 19, "0700000004", "Migraine");
        Patient fifth = new Patient(108, "Eva Shah", 52, "0700000005", "Diabetes");
        check(bst.insert(first) && bst.insert(second) && bst.insert(third)
                && bst.insert(fourth) && bst.insert(fifth), "BST inserts");
        check(!bst.insert(new Patient(105, "Duplicate", 20, "x", "x")), "BST duplicate prevention");
        check(bst.search(103) == fourth && bst.search(999) == null, "BST search");
        check(bst.delete(105) && bst.search(105) == null, "BST deletion");

        EmergencyQueue queue = new EmergencyQueue();
        queue.enqueue(second);
        queue.enqueue(third);
        check(queue.dequeue() == second && queue.dequeue() == third && queue.dequeue() == null, "Queue FIFO and empty handling");

        TreatmentStack stack = new TreatmentStack();
        TreatmentRecord treatmentOne = new TreatmentRecord(1, 101, "Brian Otieno", "Dr. Lee", "Medication", "2026-09-07");
        TreatmentRecord treatmentTwo = new TreatmentRecord(2, 110, "Chloe Mensah", "Dr. Patel", "Cast", "2026-09-07");
        stack.push(treatmentOne);
        stack.push(treatmentTwo);
        check(stack.pop() == treatmentTwo && stack.pop() == treatmentOne && stack.pop() == null, "Stack LIFO and empty handling");

        VisitHistory history = new VisitHistory();
        check(history.addVisit(new Visit(1, "2026-01-01", "Dr. Lee", "Flu", "Rest")), "Visit insertion");
        check(history.addVisit(new Visit(2, "2026-02-01", "Dr. Patel", "Check-up", "Review")), "Second visit insertion");
        check(history.searchVisit(2) != null && history.removeVisit(1)
                && history.searchVisit(1) == null, "Visit search and removal");

        System.out.println("All data structure tests passed.");
    }

    private static void check(boolean condition, String testName) {
        if (!condition) throw new AssertionError("Failed: " + testName);
        System.out.println("Passed: " + testName);
    }
}
