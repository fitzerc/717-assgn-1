package EncryptionMachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**

* Provides command line interface to encrypt a key and some number of words ...
*/
public class EncryptionMachine {
	
	/**
	* A string holding the letters in the alphabet
	* defaulted to the English alphabet.
    */
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	/**
	* An integer to keep track of for fun.
    */
	public static final int SHIFT = 3;

	/**
	* main entry-point to program
    */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DisplayIntroMessage();

		String key = PromptAndRetrieveKey(scanner);
		String encryptedKey = EncryptWord(key);
		DisplayWordAndEncryptedWord(key, encryptedKey);
		
		int wordCount = Integer.parseInt(PromptAndRetrieveWordCount(scanner));
		
		PromptRetrieveAndEncryptMessage(wordCount, scanner);
		
		DisplayOutroMessage();
	}
	
	/**
	* static method to display intro message
    */
	private static void DisplayIntroMessage() {
		System.out.println("Welcome to the CSCI717 Encryption Machine Construction \r\n"
				+ "\r\n"
				+ "The program lets you encrypt a message \r\n"
				+ "\r\n"
				+ "with a key for your recipient to decrypt!\r\n");
	}
	

	/**
	* prompt for and retrieve the key from std-in
	* @param  Scanner  Scanner instance to be used for reading std-in
    * @return String   Key retrieved from std-in
    */
	private static String PromptAndRetrieveKey(Scanner scanner) {
		return PromptAndRetrieve("Enter key: \r\n", scanner);
	}

	/**
	* prompt for and retrieve the word count from std-in
	* @param  Scanner  Scanner instance to be used for reading std-in
    * @return String   Word count retrieved from std-in
    */
	private static String PromptAndRetrieveWordCount(Scanner scanner) {
		return PromptAndRetrieve("How many words in your message?: \r\n", scanner);
	}

	/**
	* prompts for, retrieves, encrypts, and displays the message from std-in
	* @param  int      number of words expected in message
	* @param  Scanner  Scanner instance to be used for reading std-in
    */
	private static void PromptRetrieveAndEncryptMessage(int wordCount, Scanner scanner) {
		int i = 1;
		//ArrayList<String> message = new ArrayList<String>();
		//ArrayList<String> encryptedMessage = new ArrayList<String>();

		while(i <= wordCount) {
			String nextWord = PromptAndRetrieve("Next word: \r\n", scanner);
			//message.add(nextWord);
			
			String nextWordEncrypted = EncryptWord(nextWord);
			//encryptedMessage.add(nextWordEncrypted);
			
			DisplayWordAndEncryptedWord(nextWord, nextWordEncrypted);
			i++;
		}
	}
	
	/**
	* prints message containing plain text and cipher text
	* @param  String   plain text word
	* @param  Scanner  cipher text word
    */
	private static void DisplayWordAndEncryptedWord(String word, String encryptedWord) {
		System.out.println(word + " has been encrypted to: " + encryptedWord + "\r\n");
	}
	
	/**
	* encrypts a string
	* @param  String  plain text
    * @return String   cipher text
    */
	private static String EncryptWord(String plainText) {
		String cipherText = "";
		for (char letter : plainText.toCharArray()) {
			cipherText += EncryptLetter(letter);
		}
		
		return cipherText;
	}
	
	/**
	* takes letter and returns the letter at index of
	* letter in alphabet plus 3 wrapping around the end
	* @param  char   plain char
    * @return char   encrypted char
    */
	private static char EncryptLetter(char letter) {
		int letterIdx = ALPHABET.indexOf(letter);
		int cipherLetterIdx = GetCipherLetterIdx(letterIdx);

		return ALPHABET.charAt(cipherLetterIdx);
	}
	
	/**
	* takes plaintext letter index and shifts
	* to find encrypted letter index
	* @param  int    plain char index in alphabet
    * @return int   encrypted char index in alphabet
    */
	private static int GetCipherLetterIdx(int letterIdx) {
		int letterIdxPlusShift = letterIdx + SHIFT;
		
		if (letterIdxPlusShift >= ALPHABET.length()) {
			return letterIdxPlusShift - ALPHABET.length();
		}
		
		return letterIdxPlusShift;
	}
	
	/**
	* generic method to display prompt and read from std-in via scanner
	* @param  String    prompt to be displayed to user
	* @param  Scanner  Scanner instance to be used for reading std-in
    * @return String    value read from std-in
    */
	private static String PromptAndRetrieve(String prompt, Scanner scanner) {
		System.out.println(prompt);
		return scanner.nextLine();
	}

	/**
	* method to display outro message
    */
	private static void DisplayOutroMessage() {
		System.out.println("Message fully encrypted. Happy secret messaging! \r\n");
	}
}
