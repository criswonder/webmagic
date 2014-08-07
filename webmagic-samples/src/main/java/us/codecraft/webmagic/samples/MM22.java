package us.codecraft.webmagic.samples;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class MM22 implements PageProcessor {
	Map<String, Album> dataResult = Collections.synchronizedMap(new LinkedHashMap<String, Album>());

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
    	String title = page.getHtml().xpath("//*[@id='b-show']/dl/dd[1]/strong/text()").toString();
    	page.putField("title", title);

    	Album album = dataResult.get(title);

    	String url = page.getHtml().$("div#imgString img","src").toString();
    	page.putField("url", url);
    	
    	
    	if(album==null){
			album= new Album();
			album.setName(title);
			album.getUrls().add(url);
			
			if(!page.getUrl().toString().contains("-")){
	    		List<String> links = page.getHtml().$("div.pagelist").links().all();
	    		page.addTargetRequests(links);
	    		
	    		album.setUrlsSize(links.size());
	    	}
			
			dataResult.put(title, album);
		}else{
			album.getUrls().add(url);
			if(album.getUrls().size()==album.getUrlsSize()-1){
				submmitDataToServer(album);
				System.out.println(album);
			}
		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	if(args!=null && args.length>0){
    		System.out.println(args[0]);
    		 Spider.create(new MM22()).addUrl("http://www.22mm.cc/mm/bagua/PiaJHaPHmbdamHbiH.html").thread(1)
    	        .setDownloader(new SeleniumDownloader(args[0]).setSleepTime(1000))
    	        .run();    		
    	}
    	else{
    		System.out.println("beatch, no args");
    		 Spider.create(new MM22()).addUrl("http://www.22mm.cc/mm/bagua/PiaJHaPHmbdamHbiH.html").thread(1)
    	        .setDownloader(new SeleniumDownloader("/Users/kehongyun/tools/chrome/chromedriver").setSleepTime(1000))
    	        .run();
    	}
       
    
    }

	private static void submmitDataToServer(final Album album) {

		ExecutorService es =Executors.newFixedThreadPool(2);
		es.execute(new Runnable() {
			
			@Override
			public void run() {
				StringBuilder sb = new StringBuilder();
				for(String str:album.getUrls()){
					sb.append(str).append(";");
				}
				String jsonStr = OKHttpUtils.bowlingJson(album.getName(), sb.deleteCharAt(sb.length()-1).toString());
				try {
//					String result = OKHttpUtils.post("http://localhost:3000/category/list", jsonStr);
					String result = OKHttpUtils.post("http://106.187.99.142:3000/category/list", jsonStr);
					System.out.println(result);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
				}
			}
		});
	}
    
    
}

