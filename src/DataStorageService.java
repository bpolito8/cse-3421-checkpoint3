import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;



public class DataStorageService {
	
	private String GET_MOVIES = "SELECT * FROM Movie";
	private String GET_ARTIST_BY_NAME = "SELECT * FROM Creator WHERE Name LIKE ?;";
	private String GET_TRACK_BY_NAME = "SELECT * FROM Track WHERE MediaName LIKE ?;";
	private String GET_ARTISTS = "SELECT * FROM Creator";
	
	private static DataStorageService service = new DataStorageService();
	List<Artist_Track> artistTrackList;
	List<Track> trackList;
	List<Artist> artistList;
	//List<Movie> movieList;
	
	private DataStorageService() {
		// add dummy data
		trackList = new ArrayList<Track>();
		trackList.add(new Track("Gimme Shelter", "Let It Bleed", 100));
		trackList.add(new Track("Paint it Black", "Aftermath", 100));
		trackList.add(new Track("Seven Nation Army", "Elephant", 100));
		
		
		artistList = new ArrayList<Artist>();
		artistList.add(new Artist("The Rolling Stones"));
		artistList.add(new Artist("Led Zeppelin"));
		artistList.add(new Artist("Pink Floyd"));
		artistList.add(new Artist("The White Stripes"));
		
		artistTrackList = new ArrayList<Artist_Track>();
		artistTrackList.add(new Artist_Track(artistList.get(0), trackList.get(0)));
		
//		movieList = new ArrayList<Movie>();
//		movieList.add(new Movie("The Matrix"));
//		movieList.add(new Movie("Titanic"));
//		movieList.add(new Movie("Pulp Fiction"));
	}
	
	public static DataStorageService getInstance() {
		return service;
	}
	
	public List<Track> searchTracksByArtist(String artistName){
		List<Track> results = new ArrayList<Track>();
		for(int i = 0; i < artistTrackList.size(); i++) {
			if(artistTrackList.get(i).artist.name.contains(artistName)) {
				results.add(artistTrackList.get(i).track);
			}
		}
		return results;
	}
	
	public List<Track> searchTracksByName(String trackName){
		trackName = "%" + trackName + "%";
    	Connection conn = DatabaseManager.initializeDB();
    	List<Track> trackList = new ArrayList<Track>();
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_TRACK_BY_NAME, new String[] {trackName});
    	try {
			while (rs.next()) {
				trackList.add(new Track(rs.getString(1), rs.getString(2), rs.getInt(3)));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return trackList;
	}
	
	public void addNewArtist(String artistName) {
		Artist newArtist = new Artist(artistName);
		artistList.add(newArtist);
	}
	
	public void addNewTrack(String trackName, String albumName, int beatsPerMinute) {
		Track newTrack = new Track(trackName, albumName, beatsPerMinute);
		trackList.add(newTrack);
		System.out.println("Artist added");
		
	}
	
	public List<Artist> searchArtistsByName(String artistName){
		artistName = "%" + artistName + "%";
    	Connection conn = DatabaseManager.initializeDB();
    	List<Artist> artistList = new ArrayList<Artist>();
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_ARTIST_BY_NAME, new String[] {artistName});
    	try {
			while (rs.next()) {
				artistList.add(new Artist(rs.getString(1)));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return artistList;
	}
	
	public List<Movie> getMovies(){
    	Connection conn = DatabaseManager.initializeDB();
    	List<Movie> movieList = new ArrayList<Movie>();
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_MOVIES, new String[] {});
    	try {
			while (rs.next()) {
				movieList.add(new Movie(rs.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return movieList;
	}
	
	public void orderMovie(Media media, int quantity, double price) {
		OrderItem orderItem = new OrderItem(media, quantity, price);
		Order order = new Order(new Date());
		order.addOrderItem(orderItem);
		JOptionPane.showMessageDialog(null, "Movie has been ordered.");
	}
	
	public List<Artist> getArtists() {
    	Connection conn = DatabaseManager.initializeDB();
    	List<Artist> artistList = new ArrayList<Artist>();
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_ARTISTS, new String[] {});
    	try {
			while (rs.next()) {
				artistList.add(new Artist(rs.getString(1)));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return artistList;
	}
	
	public void updateArtist(String originalName, String updatedName) {
		for(int i = 0; i < artistList.size(); i++) {
			if(artistList.get(i).name.contains(originalName)) {
				artistList.get(i).name = updatedName;
			}
		}
		System.out.println("Successfully updated " + originalName + " to " + updatedName);
	}
}
