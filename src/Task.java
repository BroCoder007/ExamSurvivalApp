// This is analogoud to the Node class we create while doing any Linked List.
public class Task {
        String tId; //Task ID 
        String tName; //Task Name
        String tType; // Task type
        Task prev; // The pointer in the Node which points to the previous Node.
        Task next; // The pointer in the Node which points to the nect Node.

        // Constructor to initialize a new task node.
        Task(String id, String name, String type) {
            this.tId = id;
            this.tName = name;
            this.tType = type;
            this.prev = null;
            this.next = null;
        }
    }