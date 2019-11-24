import javafx.util.Duration;
import org.junit.Test;
import schedule.Task;

import java.util.Date;

public class TaskTest
{
    @Test
    public void lessThanDuration()
    {
        // Jan 1 2019, 12:00:00
        Date minStartTime = new Date(1546300800000L);
    
        // Jan 2 2019, 12:00:00
        Date maxEndTime = new Date(1546387200000L);
        
        // 1 hour long duration
        Duration dur = Duration.hours(1);
        
        Task testTask = new Task(minStartTime, maxEndTime, dur);
        
        assert (!testTask.getStartTime().before(minStartTime));
        assert (!testTask.getEndTime().after(maxEndTime));
        assert (testTask.getEndTime().getTime() - testTask.getStartTime().getTime() == (long) dur.toMillis());
    }
    
    @Test
    public void greaterThanDuration()
    {
        // Jan 1 2019, 12:00:00
        Date minStartTime = new Date(1546300800000L);
    
        // Jan 2 2019, 12:00:00
        Date maxEndTime = new Date(1546387200000L);
        
        Duration spanDur = Duration.millis(maxEndTime.getTime() - minStartTime.getTime());
    
        // 24 hours 1 millisecond long duration
        Duration dur = Duration.hours(24).add(Duration.ONE);
    
        Task testTask = new Task(minStartTime, maxEndTime, dur);
    
        assert (!testTask.getStartTime().before(minStartTime));
        assert (!testTask.getEndTime().after(maxEndTime));
        assert (testTask.getEndTime().getTime() - testTask.getStartTime().getTime() == (long) spanDur.toMillis());
    }
}
