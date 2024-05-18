package Person;

import java.util.Random;

public class Worker implements Runnable{
    @Override
    public void run() {
        int hourCnt = taskTime;
        for (int i = 0; i < hourCnt; i++) {
            try {
                Thread.sleep(1000);
                switch (new Random().nextInt(25)) {
                    case 0 -> {
                        if (hourCnt > 0) {
                            System.out.println(Thread.currentThread().getName() +
                                               " it is too boring, am gonna do it a bit longer");
                            hourCnt --;
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String threadName = Thread.currentThread().getName();

        if (hourCnt > 0) {
            switch (new Random().nextInt(10)) {
                case 0 -> {
                    System.out.println(threadName + " is having a Coffee Brake.");
                    hourCnt --;
                }
                case 1 -> {
                    if (hourCnt > 1) {
                        System.out.println(threadName + " is having Dinner Brake.");
                        hourCnt -= 2;
                    }
                }
                default ->
                    System.out.println(threadName + " is going to take a new Task!");
            }
        }


    }

    private String taskName;
    private int taskTime;

    public Worker(String taskName, int taskTime) {
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

//    public void executeTask() {
//        int localHourCounter = 8;
//        System.out.println("Worker is executing task: " + taskName);
//
//        WorkerThread thread = new WorkerThread(taskTime);
//
//        if (localHourCounter < taskTime) {
//
//        }
//
//        thread.start();
//
//        try {
//            thread.join();
//            localHourCounter -= taskTime;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        int rnd = new Random().nextInt(2);
//        switch (rnd) {
//            case 0 -> {
//                if (localHourCounter > 0) {
//                    System.out.println("Coffe Break!");
//                    localHourCounter--;
//                } else {
//                    System.out.println("Starting the next day");
//                    localHourCounter = 8;
//                }
//            }
//            default ->
//                System.out.println("Worker is taking the next task");
//
//        }
//    }

}
