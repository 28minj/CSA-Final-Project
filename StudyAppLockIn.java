import java.util.Scanner;

public class StudyLockInApp {
  /*
  instance variables for the scanner, Student object, and SchedulePlanner object. 
  */
  private Scanner scanner;
  private Student student;
  private SchedulePlanner planner;

  /*
  No-argument constructor. Initializes the scanner to receive user input
  */
  public StudyLockInApp(){
    scanner = new Scanner(System.in);
  }

  /*
  main method of the program. The program calls this method first upon running. Runs the rest of the program. 
  */
  public static void main(String[] args){
    StudyLockInApp app = new StudyLockInApp();
    app.run();
  }

  /*
  runs the program. This is the "main menu" where the user actually interacts. 
  */
  public void run(){
    System.out.println("--------------------");
    System.out.println("Project Study LockIn");
    System.out.println("--------------------\n");
 
    createNewPlan();

    boolean running = true;

    /*
    program runs based one what the user chooses. 
    */
    while (running){
      displayMenu();
      int choice = readInt("Choose an option: ", 1, 7);

      if (choice == 1){
        addStudyTask();
      }
      else if (choice==2){
        logStudyTime();
      }
      else if (choice==3){
        takeBreak();
      }
      else if (choice==4){
        showProgress();
      }
      else if (choice==5){
        recommendTask();
      }
      else if (choice==6){
        searchTask();
      }
      else if (choice==7){ //exits the program
        System.out.println("Exiting StudyLockIn... Good luck.");
        running = false;
      }
    }
  }

  /*
  displays the main menu, which is used in the run() method. 
  */
  private void displayMenu(){
    System.out.println();
    System.out.println("-------StudyLockIn MENU-------");
    System.out.println("1. Add study task");
    System.out.println("2. Log study time");
    System.out.println("3. Take a break");
    System.out.println("4. View progress");
    System.out.println("5. Get recommended next task");
    System.out.println("6. Search task by subject");
    System.out.println("7. Exit\n");
  }

  /*
  creates a "new plan". Inputs the student's information of their instance variales. 
  */
  private void createNewPlan(){
    System.out.print("Enter Student Name: ");
    String name = scanner.nextLine();

    int sleepHour = readInt("Enter sleep hour using 24-hour time (0-23): ", 0, 23);
    int sleepMinute = readInt("Enter sleep minute (0-59): ", 0, 59);
    int requiredStudyMinutes = readInt("Enter required study time in minutes: ", 1, 1000);

    student = new Student(name,sleepHour,sleepMinute,requiredStudyMinutes);
    planner=new SchedulePlanner(student);

    System.out.println("\nStudy plan created.");
    System.out.println(student);
  }

  /*
  adds a study task. Gets user input on a StudyTask object's instance variables. Explains information about the instance variables
  before asking for input as well.
  */
  private void addStudyTask(){
    System.out.println("Enter subject: ");
    String subject = scanner.nextLine();

    System.out.println("Enter task description: ");
    String description = scanner.nextLine();

    int estimatedMinutes = readInt("Enter estimated minutes for this task: ", 1, 1000);
    int priority = readInt("Enter priority (1=top, 5=lowest): ", 1, 5);
    StudyTask task = new StudyTask(subject,description,estimatedMinutes,priority);
    student.addTask(task);

    System.out.println("Task added: ");
    System.out.println(task);
  }

  /*
  logs study time to update the student's information. Updates study task infromation upon user input. 
  */
  private void logStudyTime(){
    int minutes=readInt("How many minutes did you study? ", 1, 1000);

    if(student.getTasks().size()>0){
      System.out.print("Enter the subject you studied, or type GENERAL: ");
      String subject = scanner.nextLine();

      if (!subject.equals("GENERAL")){
        StudyTask task = planner.searchTaskBySubject(subject);

        if(task!=null){
          task.addCompletedMinutes(minutes);
          System.out.println("Updated task: ");
          System.out.println(task);
        }
        else{
          System.out.println("No task found for that subject. The time will still count towards your overall goal though.");
        }
      }
    }

    student.addCompletedMinutes(minutes);

    System.out.println(minutes+" study minutes logged.");
    System.out.println("Remaining study time: " + student.getRemainingStudyMinutes() + " minutes.");
  }

  /*
  takes a break. Logs how many minutes the student spent on the break, then updates the instance variable for totalBreakMinutes. 
  Also prints a status method upon break. 
  */
  private void takeBreak(){
    int minutes = readInt("How many minutes was your break?", 1, 1000);
    student.addBreakMinutes(minutes);
    System.out.println(minutes + " break minutes logged.");

    int currentHour = readInt("Enter current hour using 24-hour time (0-23): ", 0, 23);
    int currentMinute = readInt("Enter current minute (0-59): ", 0, 59);

    System.out.println(planner.getStatusMessage(currentHour, currentMinute));
  }

  /*
  prints the progress of the student, so that the student can view what he has completed and what he as to do next. 
  */
  private void showProgress(){
    System.out.println("\n------Current Progress------");
    System.out.println(student);

    System.out.println("\n------Task List------");
    if (student.getTasks().size()==0){
      System.out.println("No tasks added yet.");
    }
    else{
      for (StudyTask task : student.getTasks()){
        System.out.println(task);
      }
    }

    int currentHour = readInt("Enter current hour using 24-hour time (0-23): ", 0, 23);
    int currentMinute = readInt("Enter current minute (0-59): ", 0, 59);

    System.out.println();
    System.out.println(planner.getStatusMessage(currentHour, currentMinute));
  }

  private void recommendTask(){
    StudyTask recommended = planner.recommendNextTask();

    if (recommended == null){
      System.out.println("All tasks are complete, or no tasks have been added.");
    }
    else{
      System.out.println("Recommended next task:");
      System.out.println(recommended);
    }
  }

  /*
  searches for a task.
  */
  private void searchTask(){
    System.out.print("Enter subject to search for: ");
    String subject = scanner.nextLine();
    StudyTask task = planner.searchTaskBySubject(subject);

    if (task==null){
      System.out.println("No task found for subject: " + subject);
    }
    else{
      System.out.println("Task found:");
      System.out.println(task);
    }
  }

  /*
  Reads an integer input from the user within a specific range. 
  The method repeatedly asks the user to enter a number until the input
  is valid. A valid input must be an integer and must also fall between
  the minimum and maximum values given in the parameters.
  */
  private int readInt(String prompt, int min, int max){
    int value;
    boolean valid = false;

    while(!valid){
      System.out.println(prompt);

      if (scanner.hasNextInt()){
        value=scanner.nextInt();
        scanner.nextLine();

        if(value >= min && value <=max){
          return value;
        }
        else{
          System.out.println("Please enter a number from " + min + "to " + max + ".");
        }
        
      }
      else{
        scanner.nextLine();
        System.out.println("Invalid input yo! Please enter a number.");
      }
    }
    return min;
  }



  
}
