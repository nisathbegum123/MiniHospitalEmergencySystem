public class VisitHistory {
    private VisitNode head;

    public boolean isEmpty() { return head == null; }

    public boolean addVisit(Visit visit) {
        if (visit == null || searchVisit(visit.getVisitId()) != null) return false;
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return true;
        }
        VisitNode current = head;
        while (current.getNext() != null) current = current.getNext();
        current.setNext(newNode);
        return true;
    }

    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.getVisit().getVisitId() == visitId) return current.getVisit();
            current = current.getNext();
        }
        return null;
    }

    public boolean removeVisit(int visitId) {
        if (head == null) return false;
        if (head.getVisit().getVisitId() == visitId) {
            head = head.getNext();
            return true;
        }
        VisitNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getVisit().getVisitId() == visitId) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("No visit history found for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.getVisit());
            current = current.getNext();
        }
    }
}
