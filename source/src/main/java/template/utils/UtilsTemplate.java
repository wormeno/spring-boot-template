package template.utils;

import template.exception.GenericException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UtilsTemplate {

    public static void mergeObject(Object dest, Object orig) throws IllegalAccessException, InstantiationException{
        Field[] fieldOrig = Stream.of(orig.getClass().getDeclaredFields(),orig.getClass().getSuperclass().getDeclaredFields())
                .flatMap(Stream::of)
                .toArray(Field[]::new);
/*        Field[] fieldsDest = Stream.of(dest.getClass().getDeclaredFields(),dest.getClass().getSuperclass().getDeclaredFields())
                .flatMap(Stream::of)
                .toArray(Field[]::new);*/

        for(Field field: fieldOrig){
            field.setAccessible(true);
            Object value1 = field.get(dest);
            Object value2 = field.get(orig);
            Object value = (value1 != null) ? value1 : value2;
            field.set(dest,value);
        }
      /*  for(Field fieldDest: fieldsDest){
            fieldDest.setAccessible(true);
            Object valDest = fieldDest.get(dest);
            Object valOrig = fieldDest.get(orig);
            Object value = (valOrig != null) ? valOrig : valDest;
            fieldDest.set(dest,value);
        }
*/
    }

    public static  <O> List<Field> getFields(O object) {
        List<Field> fields = new ArrayList<>();
        Class unObjeto = object.getClass();
        while (unObjeto != Object.class) {
            fields.addAll(Arrays.asList(unObjeto.getDeclaredFields()));
            unObjeto = unObjeto.getSuperclass();

        }
        return fields;
    }

    public static <O> Map<String,Object> getFieldsWithValue(O object) {
        List<Field> fields = getFields(object);
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < fields.size(); i++) {
            String name = fields.get(i).getName();
            fields.get(i).setAccessible(true);
            // if(Modifier.isPublic(fields.get(i).getModifiers())) {
            Object value = null;
            try {
                value = fields.get(i).get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(name, value);
            //  }

        }

        return map;
    }

    public static void validateCuit(String cuit)  {

        if (cuit == null || cuit.length() != 11)
            throw new GenericException("El cuit no cuenta con un formato válido");

        if (!Pattern.matches("[0-9]{11}", cuit))
            throw new GenericException("El cuit no cuenta con un formato válido");

        Integer[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};//Matriz por la cual se multiplicarán cada uno de los dígitos
        Integer sumaMod11 = 0;
        for (int i=0; i<10; i++){
            sumaMod11 += Integer.valueOf(Character.toString(cuit.charAt(i))).intValue() * serie[i];
        }

        sumaMod11 = 11 - (sumaMod11 % 11); // 11 - SUMA_MOD11;
        Integer digitoValidador = sumaMod11;
        if(sumaMod11 == 11) digitoValidador = 0;
        if(sumaMod11 == 10) digitoValidador = 9;
        Integer digitoVerificadorCuit = Integer.valueOf(Character.toString(cuit.charAt(10))).intValue();
        if(! Objects.equals(Integer.valueOf(Character.toString(cuit.charAt(10))).intValue(),digitoValidador) )
            throw new GenericException("El cuit no es válido.");
    }

}
