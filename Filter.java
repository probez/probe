package Filter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Filter {
	private Map banWordList = null;
	public static int MaxMatching;
	public static int MinMatching;
	
	public Filter() {
		banWordList = new GetWordFile().getBanMap();
	}
	
	public boolean isSenstive(String text, int matching) {
		boolean f = false;
		for(int i = 0; i < text.length(); i++) {
			int match = this.Check(text, i, matching);
			if(match > 0) {
				f = true;
			}
		}
		return f;
	}
	
	public int Check(String text, int begin, int matching) {
		boolean f = false;
		int match = 0;
		char word = 0;
		
		Map current = banWordList;
		for(int i = begin; i < text.length(); i++) {
			word = text.charAt(i);
			current = (Map) current.get(word);
			if(current!=null) {
				match++;
				if("1".equals(current.get("isEnd"))) {
					f =true;
					if(Filter.MinMatching==matching) {
						break;
					}
				}
			}
			else {
				break;
			}
		}
		if(match<2 || !f) {
			match = 0;
		}
		return match;
	}
	
	public Set<String> Loacte(String text, int matching){
		Set<String> banWordList = new HashSet<String>();
		for(int i = 0 ; i < text.length() ; i++){
			int length = Check(text, i, matching);
			if(length > 0){
				banWordList.add(text.substring(i, i+length));
				i = i + length - 1;
			}
		}
		
		return banWordList;
	}
	
}

