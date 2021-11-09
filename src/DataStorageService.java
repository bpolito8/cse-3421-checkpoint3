import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;



public class DataStorageService {
	
	private int CurrentOrderNum = 1001;
	
	private String GET_MOVIES = "SELECT * FROM Movie";
	private String GET_ARTIST_BY_NAME = "SELECT * FROM Creator WHERE Name LIKE ?;";
	private String GET_TRACK_BY_NAME = "SELECT * FROM Track WHERE MediaName LIKE ?;";
	private String GET_ARTISTS = "SELECT * FROM Creator";
	private String GET_TRACKS_FROM_ARTIST_BEFORE_YEAR = "SELECT T.* "
			+ "FROM TRACK AS T, MEDIA_CREATOR AS MC, MEDIA as M "
			+ "WHERE M.Year < ? && M.Name = T.MediaName && MC.CreatorName == ? ";
	private String GET_ALBUMS_CHECKED_OUT_BY_PATRON = "SELECT Count(*)" + 
			"FROM LibraryCardHolder L, LibraryCardHolder_InventoryItem LCH, Inventory Item II, Album A "
			+ "WHERE L.CardNumber == LCH.LibraryCardNumber && LCH.InventoryItem == II.ItemID "
			+ "&& II.MediaName == A.MediaName && LCH.LibraryCardNumber = ?";
	private String GET_MAX_CHECKOUT_PATRON = "SELECT FirstName, LastName, Count(*) "
			+ "FROM LibraryCardHolder as L, LibraryCardHolder_InventoryItem as LCH, Inventory Item as II, Movie as M "
			+ "WHERE L.CardNumber == LCH.LibraryCardNumber && LCH.InventoryItem == II.ItemID && II.MediaName 	== M.MediaName HAVING Max(M.MediaName) ";
	private String GET_MOST_POPULAR_ACTOR = "";
	private String GET_MOST_POPULAR_ARTIST = "";
	private String INSERT_ARTIST = "INSERT INTO Creator VALUES (?);";
	private String INSERT_TRACK = "INSERT INTO Track VALUES (?, ?, ?);";
	private String INSERT_ORDER = "INSERT INTO [Order] Values (?, ?)";
	private String INSERT_ORDERITEM = "INSERT INTO [OrderItem] Values (?, ?, ?, ?)";
	private String UPDATE_ARTIST = "UPDATE Creator SET Name = ? WHERE Name = ?;";
	
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
	
	private int getOrderNumber() {
		return CurrentOrderNum++;
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
	
	public List<Track> getTracksByArtistBeforeYear(String artistName, String year){
    	Connection conn = DatabaseManager.initializeDB();
    	List<Track> trackList = new ArrayList<Track>();
    	ResultSet rs = DatabaseManager.sqlQuerySpecialTypes(conn, GET_TRACKS_FROM_ARTIST_BEFORE_YEAR, 
    			new String[] {String.valueOf(year), artistName}, 
    			new DataType[] {DataType.INT, DataType.STRING});
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
		Connection conn = DatabaseManager.initializeDB();
		DatabaseManager.sqlQuery(conn, INSERT_ARTIST, new String[] {artistName});
	}
	
	public void addNewTrack(String trackName, String albumName, int beatsPerMinute) {
		Connection conn = DatabaseManager.initializeDB();
		DatabaseManager.sqlQuery(conn, INSERT_TRACK, new String[] {trackName, albumName, String.valueOf(beatsPerMinute)});
		
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
	
	public void orderMovie(String mediaName, int quantity, double price, Date estimatedArrival) {
		Connection conn = DatabaseManager.initializeDB();
		int orderNum = getOrderNumber();
		DatabaseManager.sqlQuerySpecialTypes(conn, INSERT_ORDER, 
				new String[] {String.valueOf(orderNum), String.valueOf(estimatedArrival)}, 
				new DataType[] {DataType.INT, DataType.DATE});
		DatabaseManager.sqlQuerySpecialTypes(conn, INSERT_ORDERITEM, 
				new String[] {mediaName, String.valueOf(price), String.valueOf(quantity), String.valueOf(orderNum)}, 
				new DataType[] {DataType.STRING, DataType.DECIMAL, DataType.INT, DataType.INT});
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
		Connection conn = DatabaseManager.initializeDB();
		DatabaseManager.sqlQuery(conn, UPDATE_ARTIST, new String[] {updatedName, originalName});
	}
	
	public void activateItemReceived() {
		// add item to inventory
		
		// remove item from order items
	}
	
	public int numAlbumsCheckedOutByPatron(int libraryCardNumber) {
    	Connection conn = DatabaseManager.initializeDB();
    	int numCheckouts = 0;
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_ALBUMS_CHECKED_OUT_BY_PATRON, 
    			new String[] {String.valueOf(libraryCardNumber)});
    	try {
			while (rs.next()) {
				numCheckouts = rs.getInt(1);
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return numCheckouts;
	}
	
	public Artist getMostPopularArtist() {
    	Connection conn = DatabaseManager.initializeDB();
    	Artist mostPopularArtist = null;
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_MOST_POPULAR_ARTIST, new String[] {});
    	try {
			while (rs.next()) {
				mostPopularArtist = new Artist(rs.getString(1));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mostPopularArtist;
	}
	
	public Artist getMostPopularActor() {
    	Connection conn = DatabaseManager.initializeDB();
    	Artist mostPopularArtist = null;
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_MOST_POPULAR_ACTOR, new String[] {});
    	try {
			while (rs.next()) {
				mostPopularArtist = new Artist(rs.getString(1));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mostPopularArtist;
	}
	
	public LibraryCardHolder getPatronWhoCheckedOutMostVideos() {
    	Connection conn = DatabaseManager.initializeDB();
    	LibraryCardHolder patron = null;
    	ResultSet rs = DatabaseManager.sqlQuery(conn, GET_MAX_CHECKOUT_PATRON, new String[] {});
    	try {
			while (rs.next()) {
				patron = new LibraryCardHolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return patron;
	}
}
