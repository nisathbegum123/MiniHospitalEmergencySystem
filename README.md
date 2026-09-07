# Mini Hospital Emergency Management System

## Project Description

A Java console application for CIT300 - Data Structures and Algorithms. The system models patient registration, emergency treatment, completed treatment records, and repeat hospital visits using four custom data structures.

## Objectives

- Practice object-oriented Java design.
- Implement core data structures without collection classes for the core logic.
- Model a realistic emergency department workflow.
- Provide a program that is easy to demonstrate and extend.

## Technologies Used

- Java 17 or later
- `Scanner` for console input
- No external libraries
- No `ArrayList`, `HashMap`, `TreeMap`, `TreeSet`, or Java `Stack` for core structures

## Data Structures Used

1. **Binary Search Tree:** stores patients by Patient ID. In-order traversal displays ascending IDs.
2. **Queue:** stores waiting `Patient` objects with linked nodes. Enqueue happens at the rear and dequeue at the front, giving FIFO behavior.
3. **Stack:** stores completed `TreatmentRecord` objects. Push and pop happen at the top, giving LIFO behavior.
4. **Singly Linked List:** each `Patient` owns a `VisitHistory`. Visits are linked from a head node and support insertion, search, removal, and traversal.

## Features

- Register, search, delete, and list patients.
- Reject duplicate Patient IDs.
- Add patients to and call patients from the emergency queue.
- Complete a treatment and push its record to treatment history.
- Add, search, remove, and display visits for any registered patient.
- Validate numbers, ages, menu choices, and required text fields.
- Handle empty queue, empty stack, missing patients, and missing visits safely.

## Class Structure

```text
src/
├── Main.java
├── Patient.java
├── PatientNode.java
├── PatientBST.java
├── QueueNode.java
├── EmergencyQueue.java
├── TreatmentRecord.java
├── StackNode.java
├── TreatmentStack.java
├── Visit.java
├── VisitNode.java
├── VisitHistory.java
└── DataStructureTest.java
```

## How the Structures Work

### BST

`PatientBST.insert` compares the new Patient ID with each node and moves left for smaller IDs or right for larger IDs. Search follows the same comparisons. Deletion handles a leaf, a node with one child, and a node with two children. For two children, the node is replaced with the smallest node in its right subtree.

### Queue

`EmergencyQueue` keeps `front` and `rear` references. A new patient is linked after the rear node. `dequeue` returns the front patient and advances the front reference. This preserves FIFO ordering and resets rear when the last patient leaves.

### Stack

`TreatmentStack` keeps a `top` reference. A new treatment record is linked before the old top. `pop` returns the top record and moves top to the next node. The most recent treatment is therefore removed first.

### Singly Linked List

`VisitHistory` keeps a `head` reference. A visit is added at the end, searched by visiting each node, and removed by changing the previous node's `next` link. Each patient has an independent `VisitHistory` instance.

## Compile and Run

From the project root:

```text
javac -d out src\*.java
java -cp out Main
```

Run the automated structure checks:

```text
java -cp out DataStructureTest
```

## Sample Operations

1. Register patients with IDs `105`, `101`, `110`, `103`, and `108`.
2. Display all patients. The BST prints `101`, `103`, `105`, `108`, `110`.
3. Add patients `101` and `110` to the emergency queue. Patient `101` is called first.
4. Complete the treatment for patient `101`; its record is pushed onto the stack.
5. Complete patient `110`; popping the stack returns patient `110` first.
6. Add visits `1` and `2` to patient `101`, search for visit `2`, then remove visit `1`.

## Testing

`DataStructureTest.java` checks:

- Five BST insertions, duplicate prevention, search, missing search, and deletion.
- Queue FIFO order and empty dequeue behavior.
- Stack LIFO order and empty pop behavior.
- Visit insertion, search, removal, and missing visit behavior.

## Suggested Demonstration Output

```text
Passed: BST inserts
Passed: BST duplicate prevention
Passed: BST search
Passed: BST deletion
Passed: Queue FIFO and empty handling
Passed: Stack LIFO and empty handling
Passed: Visit insertion
Passed: Second visit insertion
Passed: Visit search and removal
All data structure tests passed.
```

## Suggested GitHub Commit Sequence

1. Initial project structure
2. Add Patient class
3. Implement Patient BST insertion
4. Add BST search and traversal
5. Add BST deletion
6. Implement emergency queue
7. Implement treatment stack
8. Implement patient visit linked list
9. Add input validation
10. Add testing
11. Update README

These are suggestions only; no fake Git history is included.

## Author

**Student:** Add your name here  
**Course:** CIT300 - Data Structures and Algorithms  
**Assignment:** Individual Mini Hospital Emergency Management System

## 5-10 Minute Demonstration Guide

1. Explain that Patient IDs are the BST keys and insert five records.
2. Display them to show in-order ascending output, then search for an existing and missing ID.
3. Delete a patient and explain the BST deletion cases.
4. Enqueue three patients and dequeue them to prove FIFO behavior.
5. Complete two treatments and display or pop the history to prove LIFO behavior.
6. Add two visits to one patient, search for one, remove one, and display the updated linked list.
7. Finish by running `DataStructureTest` to show the edge cases pass.
