public class Task {
        String tId;
        String tName;
        String tType;
        Task prev;
        Task next;

        // Constructor to initialize a new task node.
        Task(String id, String name, String type) {
            this.tId = id;
            this.tName = name;
            this.tType = type;
            this.prev = null;
            this.next = null;
        }
    }