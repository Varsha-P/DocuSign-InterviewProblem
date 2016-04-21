package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GettingReady {

	HashMap<String,String> hot = new HashMap<String,String>();
	HashMap<String,String> cold = new HashMap<String,String>();
	public void addValuesToHashMaps(){
		cold.put("1","boots");
		cold.put("2","hat");
		cold.put("3","socks");
		cold.put("4","shirt");
		cold.put("5","jacket");
		cold.put("6","pants");
		cold.put("7","leaving house");
		cold.put("8","Removing PJs");
		hot.put("1","sandals");
		hot.put("2","sun visor");
		hot.put("4","t-shirt");
		hot.put("6","shorts");
		hot.put("7","leaving house");
		hot.put("8","Removing PJs");		
	}
	

	public boolean beforeShoes(String temperature,HashMap<String,Boolean> check){
	/*beforeShoes method checks the following:
		 	For cold temperature :pants-> socks -> shoes
		 							(OR) socks-> pants -> shoes
		 	For hot temperature: pants -> sandals
	 */
		if(!check.containsKey("6")) // check for pants
			return true;
		if(temperature.equals("cold"))
		{
			if(!check.containsKey("3")){ // check for socks
				return true;
			}
			else 
				return false;
		}
			
		return false;
	}
	
	public void hot(ArrayList<String> commands){
		HashMap<String,Boolean> check = new HashMap<String,Boolean>();
		check.put("8", true);
		System.out.print("Removing PJs, ");
		Boolean bool = false;
		for(int i =1;i<commands.size();i++){
			String command = commands.get(i);
			if(check.containsKey(command)){ // making sure only 1 piece of each type is worn
				bool = true;
				break;
			}
			if(!hot.containsKey(command)){
				bool = true;
				break;
			}
			switch(command){
			case "1":	if(beforeShoes("hot",check)){
							bool = true;
						}
						break;
			case "2":	if(!check.containsKey("4")){ // making sure the shirt is on before headwear
							bool = true;
						}
						break;
						
			case "7":	if(check.keySet().size()!=5){ // all pieces of clothing have to be worn before leaving the house
							bool = true;
						}
						break;
			}
			if(bool)
				break;
			check.put(command, true);
			if(!command.equals("7"))
				System.out.print(hot.get(command)+", "); // makiing sure last command does not end with a comma
			else
				System.out.println(hot.get(command));
			 
		}
		if(bool){
			if(!hot.keySet().equals(check.keySet())){ // making sure all items of clothings are on 
				System.out.println("fail");
			}
		}
	}
	public void cold(ArrayList<String> commands){
		HashMap<String,Boolean> check = new HashMap<String,Boolean>();
		check.put("8", true);
		System.out.print("Removing PJs, ");
		Boolean bool = false;
		for(int i =1;i<commands.size();i++){
			String command = commands.get(i);
			if(check.containsKey(command)){
				System.out.println("fail");
				bool = true;
				break;
			}
			switch(command){
				case "1":	if(beforeShoes("cold",check)){
								bool = true;
							}
							break;
							
				case "2":	if(!check.containsKey("4")){ // making sure the shirt is on before headwear
								bool = true;
							}
							break;
							
				case "5":	if(!check.containsKey("4")){ // making sure the shirt is on before jacket
								bool = true;
							}
							break;
							
				case "7":	if(check.keySet().size()!=7){ // all pieces of clothing have to be worn before leaving the house
								bool = true;
							}
							break;
			}
			if(bool)
				break;
			check.put(command, true);
			if(!command.equals("7"))
				System.out.print(cold.get(command)+", "); // makiing sure last command does not end with a comma
			else
				System.out.println(cold.get(command));		
		}
		if(bool){
			if(!cold.keySet().equals(check.keySet())){ // making sure all items of clothings are on
				System.out.println("fail");
			}
		}
	}
	
	public void handleInput(String input){
		addValuesToHashMaps();
        Pattern pattern = Pattern.compile("([a-zA-Z]*)((\\s*\\d*,?)*)"); // regex pattern to match input
        Matcher m = pattern.matcher(input);
        if(m.find()){ // if there's a match
        	String temperature = m.group(1).toLowerCase(); // hot or cold
        	String newS= m.group(2).replace(" ", "");// remove whitespace from commands
        	ArrayList<String> commands = new ArrayList<String>();
        	for(String s:newS.split(",")){ // split commands on ','
        		commands.add(s); 
        	}
        	if(temperature.equals("hot")){ 
        		if(commands.get(0).equals("8")) // remove pajamas first
        			hot(commands); // call 'hot' method passing commands as argument
        		else{
        			System.out.println("fail");
        		}
        	}
        	else if(temperature.equals("cold")){
        		if(commands.get(0).equals("8")) // remove pajamas first
        			cold(commands); // call 'cold' method passing commands as argument
        		else
        			System.out.println("fail");
        	}
        	else // print 'fail' if temperature is not HOT or COLD
        		System.out.println("fail");
        }
	}
}
