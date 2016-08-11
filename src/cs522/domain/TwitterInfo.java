package cs522.domain;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tweet")
public class TwitterInfo {

	public TwitterInfo() {
	}
	
	@Id
	private long tweetId;
	
	private long userId;
	private String userName;
	private String userLocation;
	private long userFollowersCount;
	private String Content;
	private Date createdAt;
	private double latitude;
	private double longitude;
	private String queryWord; 
	
	public String getQueryWord() {
		return queryWord;
	}
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	public long getTweetId() {
		return tweetId;
	}
	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public long getUserFollowersCount() {
		return userFollowersCount;
	}
	public void setUserFollowersCount(long userFollowersCount) {
		this.userFollowersCount = userFollowersCount;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "TwitterInfo [tweetId=" + tweetId + ", userId=" + userId + ", userName=" + userName + ", userLocation="
				+ userLocation + ", userFollowersCount=" + userFollowersCount + ", Content=" + Content + ", createdAt="
				+ createdAt + ", latitude=" + latitude + ", longitude=" + longitude + ", queryWord=" + queryWord + "]";
	}
	 
	
	
}
