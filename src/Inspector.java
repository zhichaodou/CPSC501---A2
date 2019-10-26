public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		
		boolean hasSuper = false;
    	boolean hasInterface = false;
    	
    	//class
    	String className = c.getName();
    	System.out.print("\n");
    	System.out.print("Class name: " + className + "\n");
		
		//super class
    	Class superClass = c.getSuperclass();
    	try {
    	System.out.print("Super class: " + superClass.getName() + "\n");
    	inspectClass(superClass, obj, recursive, 0);
    	}catch (NullPointerException e) {
			System.out.print(" Super class: NULL");
		}
 
    	//name of each interface the class implements
    	Class[] classInterface = c.getInterfaces();
    	
    	for (Class classI:classInterface) {
    		System.out.print("Interface: " + classI.getName() + "\n");
    		inspectClass(classI, obj, recursive, 0);
    	}
		
		
    }

}