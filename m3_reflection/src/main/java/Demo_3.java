import model.Device;
import model.Laptop;
import model.Laptops;
import reflection.ReflectionTools;

public class Demo_3 {
    public static void main(String[] args) {

        // TODO een varargs-parameter

       ReflectionTools.classAnalysis(Device.class,Laptop.class , Laptops.class );
        /**
        System.out.println();
        ReflectionTools.classAnalysis(Laptop.class);
        System.out.println();
        ReflectionTools.classAnalysis(Laptops.class);
         */

        try {
            Object result = ReflectionTools.runAnnotated(Device.class);
            System.out.println("Aangemaakt object door runAnnotated:");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
