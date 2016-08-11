package cs522.business;

import java.awt.event.ItemListener;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cs522.domain.TwitterInfo;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterCrawlerService {
	private static SessionFactory sessionFactory;
	private static Twitter twitter;

	static {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();

		twitter = new TwitterFactory().getInstance();

		AccessToken accessToken = new AccessToken("762733635287986176-qAQ7L0DyEEB6YNWvNkW9rvM7IXF4YFc",
				"80ZIDI6X75XtaGeOwec8E59ZpVVCc8KXlqI6Q7Ao2SecL");
		twitter.setOAuthConsumer("7nrqLDJgZQZrM33gQuai9Ja2i", "WkQxW2vXjUW2uGYw7MjBCK3ptUWuclgy2NoxOqo9GB6DRxS8T0");
		twitter.setOAuthAccessToken(accessToken);
	}
	
	public List<TwitterInfo> getAllTwitters(String keyword){
		Session session = null;
		Transaction tx = null;
		List<TwitterInfo> list = null;
		try {
			session = sessionFactory.openSession();			
			tx = session.beginTransaction();			
			list = session.createQuery("from TwitterInfo where queryword='" + keyword + "'").list();
  
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	public int crawle(String keyword) {
		List<Status> tweets = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Query query = new Query(keyword);
			query.setCount(100);
			QueryResult result;
			result = twitter.search(query);
			tweets = result.getTweets();
			for (Status tweet : tweets) {
				TwitterInfo info = new TwitterInfo();
				info.setQueryWord(keyword);
				info.setUserId(tweet.getUser().getId());
				info.setUserName(tweet.getUser().getName());
				info.setUserLocation(tweet.getUser().getLocation());
				info.setUserFollowersCount(tweet.getUser().getFollowersCount());
				info.setTweetId(tweet.getId());
				info.setContent(tweet.getText());
				info.setCreatedAt(tweet.getCreatedAt());

				GeoLocation location = tweet.getGeoLocation();
				if (location != null) {
					info.setLatitude(location.getLatitude());
					info.setLongitude(location.getLongitude());
				}
				session.saveOrUpdate(info);
			}

			tx.commit();
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
		return tweets!=null ? tweets.size() : 0;
	}
}
