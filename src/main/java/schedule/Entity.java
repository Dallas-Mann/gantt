package schedule;

import java.util.Collection;
import java.util.HashMap;

public class Entity
{
    private static HashMap<Integer, Entity> entities = new HashMap<>();
    
    private int id;
    
    public static Entity getEntity(int id)
    {
        if(!entities.containsKey(id))
        {
            entities.put(id, new Entity(id));
        }
        return entities.get(id);
    }
    
    private Entity(int id)
    {
        this.id = id;
    }
    
    public static Collection<Entity> getEntities()
    {
        return entities.values();
    }
    
    @Override
    public String toString()
    {
        return "Entity: " + id;
    }
}
