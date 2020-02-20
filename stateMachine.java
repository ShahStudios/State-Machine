package StateMachine;

// import everything thats needed to run the code
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class validator {

	public static void main(String[] args) throws Exception {

		// set inputstream to is, and same goes to br going to buffer, declare them then run a try statement and start the steps of the code
		InputStream is;
		BufferedReader br;

		try {

			// Intro Start The Machine 
			System.out.println("---------------------------------Start State Machine---------------------------------");

			// Step 1: Read StateMachine.txt [InputStream/BufferReader]
			
			// Make bufferedreader with an inputstream
			// **Read HERE**: I don't know if I did this correct: The path is:  "/Users/YungKazi/Downloads/myStateMachine.txt"      **Args** If the arg does not work 
			is = new FileInputStream(args[0]);
			br = new BufferedReader(new InputStreamReader(is));

			// Step2: Store Data Into Data Structure Using [Nested HashMap]
			
			// Use a while loop to loop through all the lines of the buffered reader
			// Split every line from the buffered reader on the space and load it into the String
			// Make String for split and Make hashmap
			String line;
			HashMap<String, HashMap<String, String>> Hashmap = new HashMap<>();

			// Basically read all the lines line by line on the state text file
			while ((line = br.readLine()) != null) {

				// Tips: HashMap
				// .put: adds a new key value pair
				// .get: gets the value for the given key

				// Now were splitting the string of the textfile in parts [0] [1] [2]
				// .put: parts[0] = same state [loading data from file]
				// .put: parts[1] = tranisition character [loading data from file]
				// .put: parts[2] = new state [loading data from file]
				String[] parts = line.split(" ");

				// 1st Line: Add [same state] with the new values from [newhashmap] into it itsarray
				// 2nd Line: Gets the value for [same state] and then add a new key value pair
				// for [transition] and [new state]
				
				// If hashmap gets a null then you make a new hashmap, which will load [transition] and [new state]
				// and then hashmap will load in [same state] [newHashMap]
				// else hashmap oldhashmap will equal hashmap that gets [same state] and oldhashmap will load [transition] [new state]
				
				// 					More Detailed Break Down
				// If Statement, Taking my hashmap putting [samestate] = to null
				// then hashmap makes a new hashmap, and then newhashmap loads [transition] and [newstate]
				// then hashmap loads [same state] and [newhashmap] 
				// If not then you will use a else statement that makes a oldhashmap that equals hashmap that gets [samestate]
				// then that oldhashmap and has it load [transition] and [new state] 				
				if (Hashmap.get(parts[0]) == null) {
					HashMap<String, String> newHashMap = new HashMap<>();
					newHashMap.put(parts[1], parts[2]);
					Hashmap.put(parts[0], newHashMap);

				} else {
					HashMap<String, String> oldHashMap = Hashmap.get(parts[0]);
					oldHashMap.put(parts[1], parts[2]);
				}
			}

			
			// Make Enhanced For-Loop
			// Iterate over the keys, entries or values
			for (String elementKey : Hashmap.keySet()) {
				for (String elementValue : Hashmap.get(elementKey).keySet())
					System.out.println(elementKey + " " + elementValue + " " + Hashmap.get(elementKey).get(elementValue));
			}

			// Vadidation Result For Machine
			System.out.println("---------------------------------Vadidation Results---------------------------------");

			// Step 3: Read myInputs.txt [InputStream/BufferReader]
			
			// Make bufferedreader with an inputstream
			// **Read HERE**: I don't know if I did this correct: The path is:  "/Users/YungKazi/Downloads/myStateMachine.txt"      **Args** If the arg does not work 
			is = new FileInputStream(args[1]);
			br = new BufferedReader(new InputStreamReader(is));

			// Basically read all the lines line by line on the myinputs text file
			// Now were splitting the string of the textfile in parts [single parts]
			while ((line = br.readLine()) != null) {

				// call function pass char array and state machine
				// Split string by using toCharArray
				char[] split = line.toCharArray();

				// Call a function in the loop that will pass a array[ char[] ] and statemachine
				// Make function
				// Pass the array [char[] split] + [statemachine array]
				// Start setting the table [SetUp Table]
				walkModel(split, Hashmap);
			}
		}

		// Catch if we have an exception while reading the files
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Step 4: Print out final results from success and failure using if/else // statements [How To Set Table]
	
	public static void walkModel(char[] input, HashMap<String, HashMap<String, String>> stateMachine) {

		// Make int i, then a string value that is [sameState = 0] and [newState = ""] for the for loop 
		int i;
		String sameState = "0";
		String newState = "";

		for (i = 0; i < input.length; i++) {
			
			// Get key needed first
			// Then find the nextState [newState] and get they key, **don't forget to add 'String' because it is a char and you aer using sameState/newState which are strings**
			// For input[char] to work you will need to have a String.char[i] for this statement to be valid
			newState = stateMachine.get(sameState).get(String.valueOf(input[i]));

			// || = condition 1 AND condition 2 must be true [made to validate correctly] 
			// newState = null || newState = " "  [if (newState == null) 
			// Null = " " for newState
			// break to 
			if (newState == null || newState.equals("")) {
				System.out.println("Failure at position " + sameState + " found char " + input[i] + ".");
				break;
			}
			// newState equal newState 
			sameState = newState;
		}

		// if statement, i = input length and then if the newState that you get is equal 999 you print the success but then if its length is not three char and it does not finish
		// you will print the string ended part 
		if (i == input.length) {
			if (newState.equals("999")) {
				System.out.println("Success");
			} else {
				System.out.println("Input string ended before success transition");
			}
		}
	}
}

/*
 * /*
 * Code Citation/References
 * https://stackoverflow.com/questions/5200187/convert-inputstream-to-bufferedreader [InputStream/BufferReader]
 * https://www.quora.com/How-can-we-create-a-nested-HashMap-in-Java [NestedHashMap] 
 * https://www.geeksforgeeks.org/split-string-java-examples/ [Split String] 
 * https://stackify.com/specify-handle-exceptions-java/ [IOException/StackTrace]
 * https://blogs.oracle.com/corejavatechtips/using-enhanced-for-loops-with-your-classes [Enhanced For-Loops]
 * https://stackoverflow.com/questions/27867598/java-hashmap-put-in-an-enhanced-for-loop-just-like-an-arraylist [Enhanced For-Loops]
 * https://stackoverflow.com/questions/1795808/and-and-or-in-if-statements [Definition For || and && Use]
 */