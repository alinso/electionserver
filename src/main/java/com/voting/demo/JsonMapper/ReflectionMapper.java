package com.voting.demo.JsonMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

public class ReflectionMapper {



    public Object jsonToObject(String jsonString, Object objectToFill) {

        ObjectCreator objectCreator  = new ObjectCreator();
        try {
            if(jsonString.contains("[")){
                return objectCreator.jsonToObjectList(jsonString,objectToFill,objectToFill.getClass());
            }
            else
                objectToFill = objectCreator.jsonToObject(jsonString,objectToFill);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return objectToFill;
    }

    public String objectToJson(Object object){
        JsonCreator jsonCreator  = new JsonCreator();
        String jsonResult;

        if (object instanceof Collection<?>)
            jsonResult   =jsonCreator.objectListToJson((List<Object>)object);
        else
            jsonResult   =jsonCreator.objectToJson(object);

        return jsonResult;
    }


}
