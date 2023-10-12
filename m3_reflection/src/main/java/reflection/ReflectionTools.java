package reflection;

import com.sun.jdi.InterfaceType;

import java.lang.reflect.*;

public class ReflectionTools {

    //@SafeVarargs
    public static void classAnalysis(Class<?>... aClass) {
        for (Class<?> myClass : aClass) {

            System.out.println("Analyse van de klasse:" + myClass.getSimpleName());
            System.out.println("=====================================");
            System.out.println("Fully qualified name:" + " " + myClass.getName());
            System.out.println("aam van de superklasse:" + " " + myClass.getSuperclass().getSimpleName());
            System.out.println("Naam van de package:" + " " + myClass.getPackageName());

            Class<?>[] intface = myClass.getInterfaces();
            System.out.print("Interfaces: ");
            for (Class<?> interface1 : intface) {
                System.out.println(interface1.getSimpleName());
            }

            Constructor[] constructors = myClass.getDeclaredConstructors();
            System.out.println("Constructors:     ");
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }

            Field[] fields = myClass.getDeclaredFields();
            System.out.print("attributen:        ");
            for (Field field : fields) {
                System.out.print(field.getName() + " " + "(" + field.getType().getSimpleName() + ")" + "  ");
            }

            System.out.println();
            Method[] methods = myClass.getDeclaredMethods();
            System.out.print("Getters :         ");
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0 && !void.class.equals(method.getReturnType()
                )) {
                    System.out.print(method.getName() + " ");
                }
            }

            System.out.println();

            System.out.print("Setters :        ");
            for (Method method : methods) {
                if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                    System.out.print(method.getName() + " ");
                }
            }
            System.out.println();

            System.out.print("Andere methode:    ");
            for (Method method : methods) {
                if (!method.getName().startsWith("set") && !method.getName().startsWith("get")
                        && method.getParameterCount() == 0 && !void.class.equals(method.getReturnType())) {
                    System.out.print(method.getName() + " ");
                }
            }
            System.out.println("\n");
        }

    }

    public static Object runAnnotated(Class<?> aClass) throws Exception {

        Constructor<?> constructor = aClass.getDeclaredConstructor();
        Object obj = constructor.newInstance();

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            CanRun canRunAnnotation = method.getAnnotation(CanRun.class);

            if (canRunAnnotation != null && method.getParameterCount() == 1 && method.getGenericParameterTypes()[0] == String.class) {
                String value = canRunAnnotation.value();
                method.invoke(obj, value);
            }
        }
        return obj ;
    }

}

