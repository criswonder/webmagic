package us.codecraft.webmagic.samples;

import java.util.ArrayList;

public class Album {
	private String name;
	private ArrayList<String> urls = new ArrayList<String>();
	private String category;
	private int urlsSize;
	
	
	public int getUrlsSize() {
		return urlsSize;
	}
	public void setUrlsSize(int urlsSize) {
		this.urlsSize = urlsSize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<String> getUrls() {
		return urls;
	}
	public void setUrls(ArrayList<String> urls) {
		this.urls = urls;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		String result = "{name:"+name+",urls:"+urls.toString()+"}";
		return result;
	}
	
}
