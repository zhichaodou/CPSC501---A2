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
    	System.out.print("Class name: " + className + "\n");
		
		//super class
    	Class superClass = classTest.getSuperclass();
    	try {
    	System.out.print("Super class: " + superClass.getName() + "\n");
    	inspectClass(superClass, obj, recursive, 0);
    	}catch (NullPointerException e) {
			System.out.print(" Super class: NULL");
		}
 
    	//name of each interface the class implements
    	Class[] classInterface = classTest.getInterfaces();
    	
    	for (Class classI:classInterface) {
    		System.out.print("Interface: " + classI.getName() + "\n");
    		inspectClass(classI, obj, recursive, 0);
    	}
		
		//constructors 
    	System.out.print("\n");
    	System.out.print("/////////////////// Constructors for class: " + className + " /////////////////// \n");
    	Constructor[] classConstructor = classTest.getDeclaredConstructors();
    	
    	for(Constructor constructor : classConstructor) {
    		constructor.setAccessible(true);
    		System.out.print("Constructor name: " + constructor.getName() + "\n");
    		
    		for(Parameter parameter: constructor.getParameters()) {
    			System.out.print("	Parameters: " + parameter.getType() + "\n");
    		}
    		System.out.print("	Method modifiers: " + Modifier.toString(constructor.getModifiers()) + "\n");
    	}
		
		//methods
    	System.out.print("\n");
    	System.out.print("/////////////////// Methods for class: " + className + " /////////////////// \n");
    	Method[] classMethods = classTest.getDeclaredMethods();
    	
    	for(Method method : classMethods) {
    		method.setAccessible(true);
    		System.out.print("Method name: " + method.getName() + "\n");
    		
    		for( Class excep : method.getExceptionTypes()) {
    			System.out.print("	Exception Thrown: " + excep.getName()+ "\n");
    		}
    		
    		for(Parameter parameter: method.getParameters()) {
    			System.out.print("	Parameters: " + parameter.getType() + "\n");
    		}
    		System.out.print("	Return type: " + method.getReturnType() + "\n");
    		System.out.print("	Method modifiers: " + Modifier.toString(method.getModifiers()) + "\n");
    	}
		
		//fields 
    	System.out.print("\n");
    	System.out.print("/////////////////// Fields for class: " + className + " /////////////////// \n");
    	Field[] classFields = classTest.getDeclaredFields();
    	
    	for (Field field: classFields) {
    		field.setAccessible(true);
    		System.out.print("Field name: " + field.getName() + "\n");
    		System.out.print("	Field type: " + field.getType() + "\n");
    		System.out.print("	Field modifers: " + Modifier.toString(field.getModifiers()) + "\n");
    		try {
				System.out.print("	Current value: " + field.get(obj).toString() + "\n");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.print("	Current value: " + "NULL" + "\n");
			}
    		
    		
    	}
		
    }

}