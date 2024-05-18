import Person.Worker;
import org.example.*;

import java.util.HashMap;
import java.util.Map;


public class TaskProcessor {
    private static final String FILE_PATH_CSV = "WeeklyPlan.csv";
    private static final int NUMBER_OF_WORKERS = 2;
    public synchronized static void process() {
        HashMap<String, Integer> tasks = TasksReader.tasksOpener(FILE_PATH_CSV);

        for (Map.Entry<String, Integer> task : tasks.entrySet()) {
            String taskName = task.getKey();
            int taskTime = task.getValue();


        }
    }
}
