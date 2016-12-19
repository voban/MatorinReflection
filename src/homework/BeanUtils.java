package homework;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владимир on 18.12.2016.
 */
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Method[] getMethods = from.getClass().getMethods();
        List<Method> getters = new ArrayList<>();
        for (Method method : getMethods)
            if ((Modifier.isPublic(method.getModifiers())) &&
                    (method.getName().matches("^get[A-Z].*")) &&
                    (!method.getReturnType().equals(void.class)) &&
                    (method.getParameterTypes().length == 0)) getters.add(method);
        Method[] setMethods = to.getClass().getMethods();
        List<Method> setters = new ArrayList<>();
        for (Method method : setMethods)
            if ((Modifier.isPublic(method.getModifiers())) &&
                    (method.getName().matches("^set[A-Z].*")) &&
                    (method.getReturnType().equals(void.class)) &&
                    (method.getParameterTypes().length == 1)) setters.add(method);
        for (Method getMethod: getters) {
            String name1 = getMethod.getName().substring(3);
            for (Method setMethod: setters) {
                String name2 = setMethod.getName().substring(3);
                if ((name1.equals(name2)) &&
                        (setMethod.getParameterTypes()[0].isAssignableFrom(getMethod.getReturnType()))) {
                    try {
                        setMethod.invoke(to, getMethod.invoke(from));
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}

