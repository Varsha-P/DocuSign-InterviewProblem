package code;

public class Test {
	public static void callHandleInput(String input, GettingReady gr){
		gr.handleInput(input);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GettingReady obj1 = new GettingReady();
		//obj1.addValuesToHashMaps();
		callHandleInput("HOT 8, 6, 6", obj1);
		callHandleInput("HOT 8, 6, 3", obj1);
		callHandleInput("COLD 8, 6, 3, 4, 2, 5, 7", obj1);
		callHandleInput("COLD 6", obj1);
		callHandleInput("HOT 8, 6, 4, 2, 1, 7", obj1);
		callHandleInput("COLD 8, 6, 3, 4, 2, 5, 1, 7", obj1);
	}

}
