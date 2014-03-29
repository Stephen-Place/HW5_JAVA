
import java.io.*;
import java.util.Queue;
import java.lang.Character;
import java.util.Scanner;
import java.lang.StringBuilder;

public class FileParser {
	
	public String[] trackedWords = {"the",
			"be",
			"and",
			"of",
			"a",
			"in",
			"to",
			"it",
			"i",
			"that",
			"for",
			"you",
			"he",
			"with",
			"an",
			"do",
			"say",
			"this"};
	public int[] trackedWordsCount = new int[18];
	public int[] worldLengthCount = new int[31];
	public Queue<String> toBeProcessed;
	
	public static void main(String[] args) {
		boolean running = true;
		
		while (running) {
				
			
		}
	}
	
	public void printMenu(){
	//I have only included Sherlock.txt in this example for the moment
		String menu = "Sherlock.txt [1]\n"
				+ "Exit[0]\n"
				+ "Please enter your choice: ";
		System.out.print(menu);
		
	}
	
	public void printReport(){
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < this.trackedWords.length; i++) {
			stringBuilder.append("There are ");
			stringBuilder.append(this.trackedWordsCount[i]);
			stringBuilder.append(" instances of ");
			stringBuilder.append(this.trackedWords[i]);
		}
		stringBuilder.append("\n\n");
		
		for (int i = 0; i < this.worldLengthCount.length; i++) {
			stringBuilder.append("There are ");
			stringBuilder.append(this.worldLengthCount[i]);
			stringBuilder.append("instances of words ");
			stringBuilder.append(i);
			stringBuilder.append(" characters in length");
		}
		
		System.out.print(stringBuilder.toString());
	}
	
	public void parseFile(String file) throws IOException
	{
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader inStream = new InputStreamReader(fileStream);
		BufferedReader inFileReader = new BufferedReader(inStream);
		String line = "";
		String[] splitLine = line.split(" ");
		while ((line = inFileReader.readLine()) != null) {
			for (String word : splitLine) {
				this.toBeProcessed.add(word);
			}
			
			for (int i = 0; i < this.toBeProcessed.size(); i++) {
				String word = this.toBeProcessed.remove();
				
				word = cleanStringBack(word);
				word = cleanStringFront(word);
				word = word.toLowerCase();
				
				keepTrackedWord(word);
				countWordLength(word);
			}
		}
	}
	
	public String getInput(){
		String action = "";
		int input = -1;
		Scanner consoleIn = new Scanner(System.in);
		boolean nonValidInput = true;
		while (nonValidInput) {
			
			
			input = consoleIn.nextInt();
			
			if(input > 0 && input < 2)
			{
				nonValidInput = false;
				switch (input) {
				case 0:
					action = "exit";
					break;
				case 1:
					action = "Sherlock.txt";
					break;
	
				default:
					//something has gone wrong
					//exit
					action = "exit";
					break;
				}
			}
		}
		consoleIn.close();
		return action;
	}
	
	public String cleanStringFront(String inString)
	{
		String returnString = "";
		char beingParsed = ' ';
		for (int i = 0; i < inString.length(); i++) {
			beingParsed = inString.charAt(i);
			if (Character.isLetterOrDigit(beingParsed))
			{
				returnString = inString.substring(0, i);
				break;
			}
		}
		return returnString;
	}
	
	public String cleanStringBack(String inString)
	{
		String returnString = "";
		char beingParsed = ' ';
		for (int i = inString.length(); i > 0; i--) {
			beingParsed = inString.charAt(i);
			if(Character.isLetterOrDigit(beingParsed))
			{
				returnString = inString.substring(i, inString.length()-1);
				break;
			}
		}
		
		return returnString;
	}
	
	public String normalizeStringToLower(String inString)
	{
		//this seems redundant now
		//I like that
		String returnString = "";
		
		returnString = inString.toLowerCase();
		
		return returnString;
	}
	
	public void keepTrackedWord(String inString){
		for (int index = 0; index < this.trackedWords.length; index++) {
			if (this.trackedWords[index] == inString)
			{
				this.trackedWordsCount[index] ++;
				break;
			}
		}
	
	}
	
	public void countWordLength(String inString){
		if (inString.length() > 0 && inString.length() < this.worldLengthCount.length)
		this.worldLengthCount[ inString.length() ] ++;
	}

}
