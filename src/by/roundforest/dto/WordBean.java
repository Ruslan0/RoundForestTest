package by.roundforest.dto;

public class WordBean{
	private String word;
	private Integer weight;
	
	public WordBean(String word) {
		this.word = word;
		this.weight = 0;
	}
	public WordBean() {
		this.weight = 0;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getWeight() {
		return weight;
	}
	
	public int compareToWord(WordBean other){
		  int result = other.getWord().compareTo( word );
		  if (result==0) other.weight++;
		  return result;
	}

	public int compareToWeight(WordBean other){
		  return other.weight-weight;
	}
}