package com.voting.demo.JsonMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

class JsonCreator {
    public String objectToJson(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> actualFieldNames = getFieldNames(fields);
        Map<String,String> fieldsAndValues = new HashMap<>();

        String value;
        for(String fieldName : actualFieldNames){
            value = getValue(fieldName,object);

            fieldsAndValues.put(fieldName,value);
        }

        String jsonResult = createJson(fieldsAndValues);
        return jsonResult;

    }

    public String objectListToJson(List<Object> objectList){
        StringBuilder builder  = new StringBuilder();
        builder.append("[");
        Integer index  =0;
        for(Object object : objectList){
            builder.append(objectToJson(object));
            index++;
            if(index<objectList.size())
            builder.append(",");
        }
        builder.append("]");
        return builder.toString();
    }


    private String createJson(Map<String,String> fieldsAndValues){

        StringBuilder builder  = new StringBuilder();
        int index  =0;
        builder.append("{");
        for (Map.Entry<String, String> entry : fieldsAndValues.entrySet())
        {
            builder.append("\"");
            builder.append(entry.getKey());
            builder.append("\":");
            builder.append(entry.getValue());

            index++;
            if(index<fieldsAndValues.size())
                builder.append(",");


        }
        builder.append("}");

        return builder.toString();
    }

    private String getValue(String fieldName, Object obj){

        String methodName  ="get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        methodName  =methodName.replace("İ","I");

        return stringifyValue(obj,methodName);
    }

    private String stringifyValue(Object obj, String methodName){
        Method method;
        String value  = null;
        try {
            method = obj.getClass().getDeclaredMethod(methodName);
            Type t  = method.getReturnType();

            if(((Class) t).getCanonicalName().equals("java.lang.String")){
                value = "\""+ method.invoke(obj) +"\"";
            }

            if(((Class) t).getCanonicalName().equals("java.lang.Integer")){

                Integer intValue = (Integer)method.invoke(obj);
                value  =intValue.toString();
            }

            if(((Class) t).getCanonicalName().equals("java.util.Date")){

                SimpleDateFormat dateFormat  = new SimpleDateFormat("dd.MM.yyyy");
                Date dateValue  = (Date)method.invoke(obj);
                value  ="\""+ dateFormat.format(dateValue)+"\"";
            }




        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return value;
    }


    private List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }
}
