package Filter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Filter {
	public Map banWordList = null;
	public static int MaxMatching;
	public static int MinMatching;
	
	public Filter() {
		banWordList = new GetWordFile().getBanMap();
	}
		
	public int Check(String text, int begin, int matching) {
		boolean f = false;
		int match = 0;
		char word = 0;
		
		String regexp = "[^a-zA-Z0-9\u4E00-\u9FA5]";
		text = text.replaceAll(regexp, "");
		Map current = banWordList;
		for(int i = begin; i < text.length(); i++) {
			word = text.charAt(i);
			current = (Map) current.get(word);
			if(current != null) {
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
		if(match < 2 || !f) {
			match = 0;
		}
		return match;
	}
	
	public Set<String> Loacte(String text, int matching){
		Set<String> banWordList = new HashSet<String>();
		String regexp = "[^a-zA-Z0-9\u4E00-\u9FA5]";
		text = text.replaceAll(regexp, "");
		for(int i = 0 ; i < text.length() ; i++){
			int length = Check(text, i, matching);
			if(length > 0){
				banWordList.add(text.substring(i, i+length));
				i = i + length - 1;
			}
		}
		
		String regexr = "[0-9]";
		text = text.replaceAll(regexr, "");
		for(int i = 0 ; i < text.length() ; i++){
			int length = Check(text, i, matching);
			if(length > 0){
				banWordList.add(text.substring(i, i+length));
				i = i + length - 1;
				}
			}
		
		String regexq = "[a-zA-Z]";
		text = text.replaceAll(regexq, "");
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

