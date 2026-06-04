import java.util.ArrayList;
import java.util.Comparator;

public class SchedulePlanner{
  /*
  instance variable for a student object. This is because the SchedulePlanner relies on data from the student user
  */
  private Student student;

  /*
  Constructor for the SchedulePlanner object, with student object as a parameter.
  */
  public SchedulePlanner(Student student){
    this.student = student; 
  }

  /*
  Calculates and returns a boolean value that returns true if the student can still finish all of their study time before 
  their sleep time, and false if the student cannot.
  */
  public boolean canStillFinish(int currentHour, int currentMinute){
    int minutesUntilSleep = student.getMinutesUntilSleep(currentHour, currentMinute);
    int remainingStudyMinutes = student.getRemainingStudyMinutes();

    return remainingStudyMinutes <= minutesUntilSleep;
  }

  /*
  Calculates the maximum amount of minutes the student can take as a break in order to not fall behind in his
  study schedule. The maxBreakAllowed is calcualted by subtracting the amount of required study minutes left from
  the amount of minutes left until sleep time.
  
  */
  public int calculateMaxBreakAllowed(int currentHour, int currentMinute){
    int minutesUntilSleep = student.getMinutesUntilSleep(currentHour, currentMinute);
    int remainingStudyMinutes = student.getRemainingStudyMinutes();

    int maxBreakAllowed = minutesUntilSleep - remainingStudyMinutes;

    if (maxBreakAllowed < 0){
      return 0;
    }

    return maxBreakAllowed;
  }

  /*
  Sorts the student's tasks by priority. This was done using the Comparator, which allows me to sort only based on 
  each StudyTask's priority value. 
  */
  public void sortTasksByPriority(){
    ArrayList<StudyTask> tasks = student.getTasks();
    tasks.sort(Comparator.comparingInt(StudyTask::getPriority));
    
  }

  /*
  returns the task that is recommended for the student to do next. This is done based on which unfinished task
  has the highest priority.
  */

  public StudyTask recommendNextTask(){
    ArrayList<StudyTask> tasks = student.getTasks();
    sortTasksByPriority();

    for (StudyTask task : tasks){
      if (!task.isComplete()){
        return task;
      }
    }
    return null;
  }

  /*
  Returns the tasks that are under the subject parameter. If there are no tasks, then return null
  */
  public StudyTask searchTaskBySubject(String subject){
    ArrayList<StudyTask> tasks = student.getTasks();
    for (StudyTask task : tasks){
      if (task.getSubject().equalsIgnoreCase(subject)){
        return task;
      }
    }

    return null;
  }
  /*
  method outputs a message that indicates the status of the student user. If the student has completed their goal,
  then a message is outputted indicating that. If the student is on track, then a messsage indicating that is returned,
  along with information on how much study time is left and how much break minutes they can take. If the student is 
  behind schedule, then the a string indicating that, and how much time the student has until sleep time and has to study
  is returned. 
  */
  public String getStatusMessage(int currentHour, int currentMinute){
    int minutesUntilSleep = student.getMinutesUntilSleep(currentHour, currentMinute);
    int remainingStudyMinutes = student.getRemainingStudyMinutes();
    int maxBreakAllowed = calculateMaxBreakAllowed(currentHour, currentMinute);

    if (student.isGoalCompleted()){
      return "Goal complete. You finished your required study time.";
    }

    if (canStillFinish(currentHour, currentMinute)){
      return "On track. You have " + minutesUntilSleep + " minutes until sleep, "
        + remainingStudyMinutes + " study minutes left, and "
        + maxBreakAllowed + " possible break minutes remaining.";
    }
    else{
      return "Behind schedule. You have only " + minutesUntilSleep 
        + " minute until sleep, but still need " + remainingStudyMinutes + "study minutes. ";
    }
  }

  




 
}
