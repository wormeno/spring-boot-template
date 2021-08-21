package template.utils;

import java.lang.reflect.Field;
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
}
