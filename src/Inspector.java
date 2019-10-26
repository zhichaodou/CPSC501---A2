import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Constructor;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class classTest = obj.getClass();
        inspectClass(classTest, obj, recursive, 0);
    }

    private void inspectClass(Class classTest, Object obj, boolean recursive, int depth) {
		
		boolean hasSuper = false;
    	boolean hasInterface = false;
    	
    	//class
    	String className = classTest.getName();
    	System.out.print("\n");
    	System.out.println("Class name: " + className);
		
		//super class
    	Class superClass = classTest.getSuperclass();
    	try {
    	System.out.println("Super class: " + superClass.getName());
    	inspectClass(superClass, obj, recursive, 0);
    	}catch (NullPointerException e) {
			System.out.println(" Super class: NULL");
		}
    	
    	System.out.print("\n");
    	System.out.println("/////////////////// Resuming Reflection for: " + className + " ///////////////////");
    	System.out.print("\n");
    	//name of each interface the class implements
    	Class[] classInterface = classTest.getInterfaces();
    	
    	for (Class classI:classInterface) {
    		System.out.println("Interface: " + classI.getName());
    		inspectClass(classI, obj, recursive, 0);
    	}
		
		//constructors 
    	System.out.print("\n");
    	System.out.println("/////////////////// Constructors for class: " + className + " ///////////////////");
    	Constructor[] classConstructor = classTest.getDeclaredConstructors();
    	
    	for(Constructor constructor : classConstructor) {
    		constructor.setAccessible(true);
    		System.out.println("Constructor name: " + constructor.getName());
    		
    		for(Parameter parameter: constructor.getParameters()) {
    			System.out.println("	Parameters: " + parameter.getType());
    		}
    		System.out.println("	Method modifiers: " + Modifier.toString(constructor.getModifiers()));
    	}
		
		//methods
    	System.out.print("\n");
    	System.out.println("/////////////////// Methods for class: " + className + " ///////////////////");
    	Method[] classMethods = classTest.getDeclaredMethods();
    	
    	for(Method method : classMethods) {
    		method.setAccessible(true);
    		System.out.println("Method name: " + method.getName());
    		
    		for( Class excep : method.getExceptionTypes()) {
    			System.out.println("	Exception Thrown: " + excep.getName());
    		}
    		
    		for(Parameter parameter: method.getParameters()) {
    			System.out.println("	Parameters: " + parameter.getType());
    		}
    		System.out.println("	Return type: " + method.getReturnType());
    		System.out.println("	Method modifiers: " + Modifier.toString(method.getModifiers()));
    	}
		
		//fields 
    	System.out.print("\n");
    	System.out.println("/////////////////// Fields for class: " + className + " ///////////////////");
    	Field[] classFields = classTest.getDeclaredFields();
    	
    	for (Field field: classFields) {
    		field.setAccessible(true);
    		System.out.println("Field name: " + field.getName() );
    		System.out.println("	Field type: " + field.getType() );
    		System.out.println("	Field modifers: " + Modifier.toString(field.getModifiers()));
    		try {
				System.out.print("	Current value: " + field.get(obj).toString() + "\n");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("	Current value: NULL");
			}
    		
    		
    	}
    	System.out.print("\n");
		System.out.println("/////////////////// End of reflection for: " + className + " ///////////////////");
		
    }

}