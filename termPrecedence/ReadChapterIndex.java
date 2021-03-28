package termPrecedence;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import PersonalizedLearning.lib;
public class ReadChapterIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ArrayList <WordPrecedence> wpList = new ArrayList<WordPrecedence>();
	static Map<Integer,ArrayList<WordPrecedence>> precedenceMap= new HashMap<Integer,ArrayList<WordPrecedence>>();
	static ArrayList<Integer> chapterPageNum= new ArrayList<Integer>();
	static ArrayList<TOC> toc = new ArrayList<TOC>();
	public static ArrayList<Integer> readFrontIndex(){
		//For time being done manually, automate Later, This is for security course
		
		chapterPageNum.add(1);
		chapterPageNum.add(55);
		chapterPageNum.add(113);
		chapterPageNum.add(173);
		chapterPageNum.add(221);
		chapterPageNum.add(269);
		chapterPageNum.add(327);
		chapterPageNum.add(387);
		chapterPageNum.add(445);
		chapterPageNum.add(487);
		return chapterPageNum;
	}
	
	public static void readBackIndex(String fileName){
		
		Scanner scan = null;
		try {
			scan = new Scanner( new File(fileName));
			String currentLine;
			while (scan.hasNextLine()) {
				currentLine = scan.nextLine();
				System.out.println(currentLine);
				createMapping(currentLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scan.close();
		}

	}
	public static void createMapping(String currentLine ){
		//Parse data
		String [] tokens = currentLine.split(",");
		String word;
		Integer pageNum;
		if((currentLine.contains("see")==false) && (currentLine.contains("Index")==false)&&(tokens.length>1)){
			word = tokens[0];
			//create arrayList for page numbers, loop on all the tokens and set the arrayList
			ArrayList<Integer> pageArr = new ArrayList<Integer>();
			for (int i=1;i<tokens.length;i++){
				if(tokens[i].contains("–")){
					String[] token = tokens[i].split("–");
					pageArr.add(Integer.parseInt(token[0].trim()));
					pageArr.add(Integer.parseInt(token[1].trim()));
					//pageArr
				}else{
					pageArr.add(Integer.parseInt(tokens[i].trim()));
					
				}

			}
			WordPrecedence wp = new WordPrecedence();
			wp.pageNumbers = pageArr; 
			wp.precedence = setPrecedence(pageArr);
			wp.word = word;
			wpList.add(wp);
		}

	}
	
	public static void readTOC(String fileName){
		Scanner scan = null;
		try {
			scan = new Scanner( new File(fileName));
			String currentLine;
			while (scan.hasNextLine()) {
				currentLine = scan.nextLine();
				System.out.println(currentLine);
				parseTOC(currentLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scan.close();
		}

	}
	public static void parseTOC(String currentLine){
		String [] tokens = currentLine.split(" ");
		TOC tocObj = new TOC();
		tocObj.chapterNum = Character.getNumericValue(tokens[0].charAt(0));
		if(tokens[0].length()>1){
			tocObj.isChapterTitle = false;
		}else{
			tocObj.isChapterTitle = true;
		}
		String temp ="";
		for(int i=1;i<tokens.length-1;i++){
			 temp = temp+tokens[i];
		}
		tocObj.topic = temp;
		tocObj.pageNum = Integer.parseInt(tokens[(tokens.length)-1]);
		toc.add(tocObj);
		//At a later stage add this to give some weight to the precedence
	}
	
	public static int setPrecedence(ArrayList<Integer>pageArr){
		int precedence = 0;
		int [] termOccuredInChapters = new int[pageArr.size()];
		
		for(int i=0;i<pageArr.size();i++){
			int j;
			for( j= 0;j<chapterPageNum.size();j++){
				if(pageArr.get(i)<=chapterPageNum.get(j)){
					termOccuredInChapters[i] = j;
					break;
				}
			}
			if(termOccuredInChapters[i]==0){
				termOccuredInChapters[i] = j;
			}
		}
		precedence = getHighFreqChapter(termOccuredInChapters);
		return precedence;
		
		
	}
	public static int getHighFreqChapter(int[]termOccuredInChapters){
		Map <Integer, Integer> highFreqChapter = new HashMap<Integer,Integer>();
		int popularChapter = 0;
		int popularCnt = 0;
		for(int chapter:termOccuredInChapters){
			if(highFreqChapter.containsKey(chapter)){
				int count = highFreqChapter.get(chapter);
				count = count+1;
				highFreqChapter.put(chapter, count);
				if(popularCnt<count){
					popularChapter = chapter;
					popularCnt = count;
				}
			}else{
				int count = 1;
				if(popularCnt<count){
					popularChapter = chapter;
					popularCnt = count;
				}
				highFreqChapter.put(chapter, count);

			}
		}
		return popularChapter;

	}
	
	public static void main(String[] args){
		//readFrontIndex();
		//readBackIndex("C:/Sheetal/Research/Personalization/PersonalizedLearning/lib/Introduction to Computer Security.txt");
		readTOC("C:/Sheetal/Research/Personalization/PersonalizedLearning/lib/TOC.txt");
		/*
		for(int i=0;i<wpList.size();i++){
			int precedence = wpList.get(i).precedence;
			//System.out.println("Word = "+wpList.get(i).word+" Precedence = "+precedence);
			if(precedenceMap.containsKey(precedence)){
				ArrayList<WordPrecedence> arr = precedenceMap.get(precedence);
				arr.add(wpList.get(i));
				//precedenceMap.replace(precedence, arr);
				}else{
				ArrayList<WordPrecedence> arr = new ArrayList<WordPrecedence>();
				arr.add(wpList.get(i));
				precedenceMap.put(precedence, arr);
			}
		}
		for(int i=1;i<=precedenceMap.size();i++){
			System.out.println("Precedence Level .."+i+"\n");
			ArrayList<WordPrecedence> arr = precedenceMap.get(i);
			Collections.sort(arr,new PageNumberComparator());
			for(int j=0;j<arr.size();j++){
				System.out.println(arr.get(j).word+", Page Number: "+arr.get(j).pageNumbers.get(0));
				
			}
			System.out.println("********************************");
		}
		*/
		/*
		Collections.sort(wpList,new PrecedenceComparator());
		System.out.println("Sorted with Comparator");
		System.out.println(wpList);
		*/
	}
	
	
	
}
