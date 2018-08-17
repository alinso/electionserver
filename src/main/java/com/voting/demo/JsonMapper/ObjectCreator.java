package com.voting.demo.JsonMapper;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class ObjectCreator {

  public Object jsonToObject(String JsonString, Object objectToFill) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

    String[] parts = JsonString.split(",");
    String fieldName;
    String value;


    for (String part : parts) {
      String[] parts1 = part.split(":");
      fieldName = parts1[0].replace("\"", "").replace("{", "");
      value = parts1[1].replace("\"", "").replace("}", "");

      Field field = null;
      try {
        field = objectToFill.getClass().getDeclaredField(fieldName);
        assert field != null;
        Object type = field.getType();

        objectToFill = setValue(fieldName, value, objectToFill, type);
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }


    }
    return objectToFill;

  }


  public <T> List<T> jsonToObjectList(String jsonString, Object objectToFill, Class<T> objectClass) {

    List<T> objects = new ArrayList<>();

    String[] parts = jsonString.split(",");
    String singleObjectJson;
    for (String part : parts) {
      singleObjectJson = part.replace("[", "").replace("]", "");

      try {
        if (objectClass.getCanonicalName().equals("java.lang.Integer")) {
          objectToFill = Integer.parseInt(singleObjectJson);
        } else if (objectClass.getCanonicalName().equals("java.lang.String")) {
          objectToFill = singleObjectJson.toString();
        }
        //insert other primitives here
        //...
        else {
          // non-primitive types
          objectToFill = jsonToObject(singleObjectJson, objectToFill);
        }

        objects.add(objectClass.cast(objectToFill));

      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        e.printStackTrace();
      }

    }
    return objects;
  }


  private Object setValue(String fieldName, String value, Object obj, Object type) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    Field field = obj.getClass().getDeclaredField(fieldName);
    methodName = methodName.replace("İ", "I");

    if (value.equals("null"))
      value = null;

    if (((Class) type).getCanonicalName().equals("java.lang.String")) {
      Method method = obj.getClass().getDeclaredMethod(methodName, String.class);
      method.invoke(obj, value);
    } else if (((Class) type).getCanonicalName().equals("java.lang.Integer")) {
      Method method = obj.getClass().getDeclaredMethod(methodName, Integer.class);
      assert value != null;
      Integer intValue = Integer.parseInt(value);
      method.invoke(obj, intValue);
    } else if (field.getDeclaredAnnotations()[0].toString().equals("@javax.persistence.Enumerated(value=ORDINAL)")) {
      Method method = obj.getClass().getDeclaredMethod(methodName, Integer.class);
      assert value != null;
      Integer intValue = Integer.parseInt(value);
      method.invoke(obj, intValue);
    }
    //YOU CAN INSERT OHTER PRIMITIVE TYPES HERE

    return obj;

  }
}
