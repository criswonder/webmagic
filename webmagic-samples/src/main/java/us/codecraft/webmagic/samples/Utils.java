package us.codecraft.webmagic.samples;

import java.util.List;

public class Utils {
	public static void printList(List lst){
		if(lst!=null && lst.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Object obj:lst){
				sb.append(obj.toString()).append(",");
			}
			if(sb.length()>0)
				sb.deleteCharAt(sb.length()-1);
			System.out.println(sb);
		}
	}
}
