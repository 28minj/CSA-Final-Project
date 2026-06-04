import java.util.ArrayList;
public class Student{
  /*
  Instance variables for the student, containing their name, sleep time, required study time, completed study time
  , and total break time.
  */
  private String name;
  private int sleepHour;
  private int sleepMinute;
  private int requiredStudyMinutes;
  private int completedStudyMinutes;
  private int totalBreakMinutes;
  private ArrayList<StudyTask> tasks = new ArrayList<>();

  /*
  Constructor for the student object. The completedStudyMinutes and totalBreakMinutes are just set as 0, since at
  the start, the student hasn't done anything. 
  */
  public Student(String name, int sleepHour, int sleepMinute, int requiredStudyMinutes){
    this.name=name;
    this.sleepHour=sleepHour;
    this.sleepMinute=sleepMinute;
    this.requiredStudyMinutes=requiredStudyMinutes;
    this.completedStudyMinutes=0;
    this.totalBreakMinutes=0;
    this.tasks = new ArrayList<StudyTask>();
  }

  /*
  Adds task in the tasks array
  */
  public void addTask(StudyTask task){
    tasks.add(task);
  }

  /*
  method adds to the completedStudyMinutes variable. If the completedStudyMinute is greater than the requiredStudyMinutes,
  then completedStudyMinutes is just set to requiredStudyMinutes, so that later on the remainingStudyMinutes doesn't
  become negative. 
  */
  public void addCompletedMinutes(int minutes){
    if(minutes>0){
      completedStudyMinutes+=minutes;

      if(completedStudyMinutes > requiredStudyMinutes){
        completedStudyMinutes = requiredStudyMinutes;
      }
    }
  }

  /*
  Logs break minutes of the student to the totalBreakMinutes instance variable. 
  */
  public void addBreakMinutes(int minutes){
    if (minutes>0){
      totalBreakMinutes+=minutes;
    }
  }

  /*
  getter method that calculates the remaining study minutes by subtracting completed study minutes from required study 
  minutes. 
  */
  public int getRemainingStudyMinutes(){
    return requiredStudyMinutes - completedStudyMinutes;
  }

  /*
  Getter method for minutes left until sleep, after getting a currentHour and currentMinute parameter input. 
  If the sleep time is before midnight, then just subtracts minute values. if the sleep time is after midnight,
  then adds 24 hours to the sleep time before subtracting. 
  */
  public int getMinutesUntilSleep(int currentHour, int currentMinute){
    int currentTotalMinutes = currentHour * 60 + currentMinute;
    int sleepTotalMinutes = sleepHour * 60 + sleepMinute;

    if (sleepTotalMinutes >= currentTotalMinutes){
      return sleepTotalMinutes - currentTotalMinutes;
    }
    else{
      return (24*60 - currentTotalMinutes) + sleepTotalMinutes;
    }
  }

  /*
  returns whether or not the goal studying time has been fulfilled
  */
  public boolean isGoalCompleted(){
    return completedStudyMinutes >= requiredStudyMinutes;
  }

  //gets student name
  public String getName(){
    return name;
  }

  //gets student's sleep hour
  public int getSleepHour(){
    return sleepHour;
  }

  //gets student's sleep minute
  public int getSleepMinute(){
    return sleepMinute;
  }

  //gets student's required stuy minutes
  public int getRequiredStudyMinutes(){
    return requiredStudyMinutes;
  }

  //gets student's completed study minutes
  public int getCompletedStudyMinutes(){
    return completedStudyMinutes;
  }
  
  //get student's total break minutes
  public int getTotalBreakMinutes(){
    return totalBreakMinutes;
  }

  //gets student's tasks
  public ArrayList<StudyTask> getTasks(){
    return tasks;
  }

  //gets student's sleep time as a string output in 24 hour digital clock format. 
  public String getSleepTimeString(){
    String minuteString;

    if (sleepMinute<10){
      minuteString = "0" + sleepMinute;
    }
    else{
      minuteString = "" + sleepMinute;
    }

    return sleepHour + ":" + minuteString;
  }

  /*
  toString method returns a string containing all the information about the student, indicating the each of their 
  instance variable values. 
  */
  public String toString(){
    return "Student: " + name
      + "\nSleep Time: " + getSleepTimeString()
      + "\nRequired Study Time: " + requiredStudyMinutes + " min"
      + "\nCompleted Study Time: " + completedStudyMinutes + " min"
      + "\nRemaining Study Time: " + getRemainingStudyMinutes() + " min"
      + "\nTotal Break Time: " + totalBreakMinutes + " min";
  }












  
}
