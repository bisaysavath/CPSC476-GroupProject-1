package cpsc_476_Project1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlDetails {

	private String longUrl;
	
	private int clicks;
	
	private List<String> usernames = new ArrayList<>();
	 	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public List<String> getUsernames() {
		return usernames;
	}
	public void addUserName(String username) 
	{		
		this.usernames.add(username);
	}
	
}
