import java.util.Scanner;

// 
//    Exam Survival App Implementation with DSA
//   
//    LOGIC AND DESIGN
//    This program implements a dynamic task manager using a custom-built Doubly
//    Linked List (DLL) ADT. A Doubly L
//    
//   
//    The implementation consists of three main components:
//    1.  Task: A Node representing a single task, holding data and 'next'/'prev' pointers.
//    2.  TaskManagementSystem: The Doubly Linked List class. It manages the `head` of the list and contains
//    all logic for list manipulation.
//    3.  ExamSurvivalApp: The main class for handling user input and calling methods on the
//    TaskManagementSystem instance.
//   
//    TIME COMPLEXITY (I used only head).
//    - Add Task At End('A'): O(n). To add a new task, the list must be traversed from the
//    `head` to the end to find the last node.
//    - Remove Task ('R'): O(n). The list must be traversed to find the task by its ID.
//    - Print - 'P' : O(n). A full traversal from head to end is required.
//    - Reverse Print ('REV'): O(n^2). Requires a traversal to find the last node, then
//    another traversal backward to print.
//   
public class ExamSurvivalApp {
    //    Represents a single task in our list. This is the "Node" of our ADT.
    //    This class is the Doubly Linked List ADT implementation. It encapsulates
    //    all the logic for manipulating the list of tasks using only a head pointer.
    
    static class TaskManagementSystem {
        private Task head = null;
        //    Adds a new task to the end of the list. This is now an O(n) operation.
          
        public void addTaskAtEnd(String id, String name, String type) {
            Task newNode = new Task(id, name, type);
            if (head == null) {
                // The list is empty.
                head = newNode;
            } else {
                // Traverse to the end of the list.
                Task last = head;
                while (last.next != null) {
                    last = last.next;
                }
                // Append the new node after the last node.
                last.next = newNode;
                newNode.prev = last;
            }
            System.out.printf("Task %s (%s - %s) added.\n", id, name, type);
        }

         
        //    Removes a task by its ID. This requires traversing the list.
          
        public void removeTask(String id) {
            Task currentPointer = head;
            // Traverse the list to find the node with the matching ID.
            while (currentPointer != null && !currentPointer.tId.equals(id)) {
                currentPointer = currentPointer.next;
            }

            if (currentPointer == null) {
                // We reached the end of the list and didn't find the task.
                System.out.printf("Task %s not found.\n", id);
                return;
            }

            // Unlink the found node from the list.
            if (currentPointer.prev != null) {
                currentPointer.prev.next = currentPointer.next;
            } else {
                // The node to remove is the head.
                head = currentPointer.next;
            }

            if (currentPointer.next != null) {
                currentPointer.next.prev = currentPointer.prev;
            }
            // No tail pointer to update.

            System.out.printf("Task %s removed.\n", id);
        }

        // Prints all tasks from head to tail.
        public void print() {
            if (head == null) {
                System.out.println("Task list is empty");
                return;
            }
            Task currentPointer = head;
            while (currentPointer != null) {
                System.out.printf("%s %s %s\n", currentPointer.tId, currentPointer.tName, currentPointer.tType);
                currentPointer = currentPointer.next;
            }
        }

        // Prints all tasks in reverse. This now requires finding the end first.
        public void printReverse() {
            if (head == null) {
                System.out.println("Task list is empty");
                return;
            }
            // First, traverse to the end of the list to find the last node.
            Task last = head;
            while (last.next != null) {
                last = last.next;
            }

            // Now, traverse backward from the last node.
            Task currentPointer = last;
            while (currentPointer != null) {
                System.out.printf("%s %s %s\n", currentPointer.tId, currentPointer.tName, currentPointer.tType);
                currentPointer = currentPointer.prev;
            }
        }
    }

    
     //Main driver method. Handles input and orchestrates calls to the TaskManagementSystem.
     
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManagementSystem taskList = new TaskManagementSystem();
        
        System.out.println("--- Exam Survival App ---");
        System.out.print("Enter the number of operations: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.printf("\nEnter operation %d of %d (e.g., 'A T01 Name Type', 'R T01', 'P', 'REV'):\n> ", i + 1, n);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Operation cannot be empty. Please try again.");
                i--; // Decrement to re-do this iteration
                continue;
            }

            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "A":
                    if (parts.length != 4) {
                        System.out.println("Invalid input for Add operation. Expected format: A <ID> <Name> <Type>");
                        continue;
                    }
                    taskList.addTaskAtEnd(parts[1], parts[2], parts[3]);
                    break;

                case "R":
                    if (parts.length != 2) {
                        System.out.println("Invalid input for Remove operation. Expected format: R <ID>");
                        continue;
                    }
                    taskList.removeTask(parts[1]);
                    break;

                case "P":
                    taskList.print();
                    break;

                case "REV":
                    taskList.printReverse();
                    break;

                default:
                    System.out.println("Invalid operation. Please use 'A', 'R', 'P', or 'REV'.");
            }
        }
        System.out.println("\nAll operations completed. Exiting program.");
        scanner.close();
    }

        
}
