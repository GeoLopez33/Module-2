
public class Wordie {

	String word;
	int count;
	
	public Wordie(String word) {
		this.word=word;
		count=1;
	}
	
	public Wordie(String word, int num) {
		this.word=word;
		count=num;
	}
	
	public void getCountS() {
		
		System.out.println(word + ": " + count);
	}
	
	public int getCount() {
		
		return count;
	}
	
	public void upCount() {
		count++;
	}
	
	@Override
	public String toString() {

		return word;
	}
	
	public void setZero() {
		count=0;
	}
}
