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
		
    }

}