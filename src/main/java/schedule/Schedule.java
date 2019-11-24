package schedule;

import javafx.util.Duration;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

public class Schedule
{
    private Map<Entity, Collection<Task> > entitySchedules;
    private static long FIVE_MIN_MILLISECONDS = 5 * 60 * 1000;
    private static long ONE_HOUR_MILLISECONDS = 60 * 60 * 1000;
    
    public Schedule(Collection<Entity> entities, Date startTime, Date endTime, int numberOfTasks)
    {
        entitySchedules = new HashMap<>();
        for(Entity entity : entities)
        {
            entitySchedules.put(entity, generateSchedule(startTime, endTime, numberOfTasks));
        }
    }
    
    private static TreeSet<Task> generateSchedule(Date startTime, Date endTime, int numberOfTasks)
    {
        TreeSet<Task> entitySchedule = new TreeSet<>();
        Random rand = new Random();
        
        for(int curTask = 1; curTask <= numberOfTasks; curTask++)
        {
            long taskMillis = ((long) rand.nextDouble() * (ONE_HOUR_MILLISECONDS - FIVE_MIN_MILLISECONDS));
            Duration taskDur = Duration.millis(taskMillis);
            entitySchedule.add(new Task(startTime, endTime, taskDur));
        }
        
        return entitySchedule;
    }
    
    public String toString()
    {
        String result = new String();
        Collection<Entity> entities = Entity.getEntities();
        for(Entity entity : entities)
        {
            result += (entity.toString() + "\n\t" + entitySchedules.get(entity).toString());
        }
        
        return result;
    }
}
