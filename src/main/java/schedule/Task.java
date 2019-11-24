package schedule;

import javafx.util.Duration;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Task implements Comparable<Task>
{
    private Date startTime = null;
    private Date endTime = null;
    private static Random rand = new Random();
    
    // Create a task starting at startTime, ending at endTime
    public Task(Date startTime, Date endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Generates a task for the given duration that occurs between the given start time and end time.
    // If the given duration is greater than the time span then the task will cover the given time span instead.
    public Task(Date minStart, Date maxEnd, Duration requestedDur)
    {
        Duration taskDir;
        Duration spanDur = new Duration(maxEnd.getTime() - minStart.getTime());
        if(requestedDur.isIndefinite() || requestedDur.greaterThan(spanDur))
        {
            taskDir = spanDur;
        }
        else
        {
            taskDir = requestedDur;
        }
        
        long minTaskStartTime = minStart.getTime();
        long maxTaskStartTime = maxEnd.getTime() - (long) taskDir.toMillis();
        
        long taskStartTime = minTaskStartTime + ((long) (rand.nextDouble() * (maxTaskStartTime - minTaskStartTime)));
        long taskEndTime = taskStartTime + (long) taskDir.toMillis();
        
        this.startTime = new Date(taskStartTime);
        this.endTime = new Date(taskEndTime);
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    @Override
    public int compareTo(Task other)
    {
        // This task starts before the other task
        if(this.getStartTime().before(other.getStartTime()))
        {
            return -1;
        }
        // This task starts after the other task
        else if(this.getStartTime().after(other.getStartTime()))
        {
            return 1;
        }
        // Both tasks start at the same time
        else
        {
            // This task ends before the other task
            if(this.getEndTime().before(other.getEndTime()))
            {
                return -1;
            }
            // This task ends after the other task
            else if(this.getEndTime().after(other.getEndTime()))
            {
                return 1;
            }
            // Both tasks end at the same time
            else
            {
                return 0;
            }
        }
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other == null ||
           !(other instanceof Task))
        {
            return false;
        }
        else
        {
            Task otherTask = (Task) other;
            return this.getStartTime().equals(otherTask.getStartTime()) &&
                    this.getEndTime().equals(otherTask.getEndTime());
        }
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(startTime, endTime);
    }
    
    @Override
    public String toString()
    {
        return "[ " + startTime.toString() + " - " + endTime.toString() + " ]";
    }
}
