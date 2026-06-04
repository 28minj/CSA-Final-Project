public class StudyTask{
  /*
  Instance variables of the StudyTask object
  */
  private String description;
  private String subject;
  private int estimatedMinutes;
  private int completedMinutes;
  private int priority;

  /*
  Constructor for the StudyTask object, sets completedMinutes at 0 because initially, the user has not completed any work.
  */
  public StudyTask(String subject, String description, int estimatedMinutes, int priority){
    this.subject=subject;
    this.description=description;
    this.estimatedMinutes=estimatedMinutes;
    this.completedMinutes=0;
    this.priority=priority;
  }

  /*
  Logs completed minutes of studying the StudyTask. 
  */
  public void addCompletedMinutes(int minutes){
    if (minutes>0){
      completedMinutes+=minutes;
      if (completedMinutes>estimatedMinutes){
        completedMinutes=estimatedMinutes;
      }
    }
    
  }

  //returns a boolean value that is true if the task has been completed, and false if not
  public boolean isComplete(){
    return completedMinutes >= estimatedMinutes;
  }

  //getter method for the remaining minutes until completion of the task
  public int getRemainingMinutes(){
    return estimatedMinutes - completedMinutes;
  }

  //getter method for the subject of the task
  public String getSubject(){
    return subject;
  }

  //getter method for the tasks's description
  public String getDescription(){
    return description;
  }

  //getter method for the tasks's estimated study time for completion
  public int getEstimatedMinutes(){
    return estimatedMinutes;
  }

  //getter method for the completed minutes for the study task
  public int getCompletedMinutes(){
    return completedMinutes;
  }

  //getter method for the priority of the task
  public int getPriority(){
    return priority;
  }

  //returns a string containing the information of the instance variables of the study task object
  public String toString(){
    String status;

    if(isComplete()){
      status="Complete";
    }
    else{
      status="Incomplete";
    }

    return subject + " - " + description + " | Estimated: " + estimatedMinutes + " min"
      + " | completed: " + completedMinutes + " min"
      + " | Remaining: " + getRemainingMinutes() + " min"
      + " | Priority: " + priority
      + " | Status: " + status;
  }








  
}
