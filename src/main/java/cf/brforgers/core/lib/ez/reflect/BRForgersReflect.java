package cf.brforgers.core.lib.ez.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Reflection done more easily
 */
public class BRForgersReflect {
    /**
     * Transform a Class to a Map.
     *
     * @param clazz the class to be reflected
     * @param inst  an instance of the class (null > Only static fields)
     * @param <T>   the type of the class and the object
     * @return the Map
     */
    public static <T> Map<String, Object> asMap(Class<? extends T> clazz, T inst) {
        Map<String, Object> result = new HashMap<String, Object>();
        Field[] declaredFields = getAllFields(clazz);
        for (Field field : declaredFields) {
            try {
                if (!Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);
                    result.put(field.getName(), field.get(inst));
                }
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * Transform a Class to a String Map.
     * @param clazz the class to be reflected
     * @param inst an instance of the class (null > Only static fields)
     * @param <T> the type of the class and the object
     * @return the Map with all values toString()
     */
    public static <T> Map<String, String> asStringMap(Class<? extends T> clazz, T inst) {
        Map<String, String> result = new HashMap<String, String>();
        Field[] declaredFields = getAllFields(clazz);
        for (Field field : declaredFields) {
            try {
                if (!Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);
                    result.put(field.getName(), field.get(inst).toString());
                }
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * Get all Fields from the class (Private and Public from Class and all Subclasses)
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<Field>();
        do {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);
        return fields.toArray(new Field[fields.size()]);
    }

    public static Map<String, Field> mapFields(Field[] fields) {
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }
}
