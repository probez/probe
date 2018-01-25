package Filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GetWordFile {
	private String encode = "GBK";
	public HashMap banWordList;

	public GetWordFile() {
		super();
	}

	private Set<String> inputBanWord() throws Exception {
		Set<String> set = null;
		File text = new File("D:\\QcUsers\\Win7\\Administrator\\Desktop\\敏感词库\\色情类.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(text), encode);
		try {
			if (text.isFile() && text.exists()) {
				set = new HashSet<String>();
				BufferedReader buffer = new BufferedReader(read);
				String input = null;
				while ((input = buffer.readLine()) != null) {
					set.add(input);
				}
			} else {
				throw new Exception("Need BanWordList");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			read.close();
		}
		return set;
	}

	private void addBanWord(Set<String> banWordsSet) {
		banWordList = new HashMap(banWordsSet.size());
		String key = null;
		Map current = null;
		Map <String, String>newWord=null;
		Iterator<String> iterator=banWordsSet.iterator();
		while(iterator.hasNext()) {
			key=iterator.next();
			current = banWordList;
			for(int i = 0; i < key.length(); i++){
				char chKey = key.charAt(i);
				Object wordMap =current.get(chKey);
				if(wordMap!=null) {
					current=(Map)wordMap;
				}else {
					newWord = new HashMap<String, String>();
					current.put(chKey, newWord);
					current = newWord;
				}
				if(i == key.length()-1) {
					current.put("isEnd", "1");
				}
			}
		}
	}
	
	public Map getBanMap() {
		try {
			Set<String> banWord = inputBanWord();
			addBanWord(banWord);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return banWordList;
	}
}

