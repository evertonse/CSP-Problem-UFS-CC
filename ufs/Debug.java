public class Debug {
	static public boolean DEBUG = true;

	static public void debug(String str){
		if(Debug.DEBUG)
			System.out.println(str);
	}
}
