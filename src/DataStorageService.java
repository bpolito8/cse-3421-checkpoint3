import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			+ "WHERE M.Year < ? and M.Name = T.MediaName and MC.CreatorName == ? ";
	private String GET_ALBUMS_CHECKED_OUT_BY_PATRON = "SELECT Count(*)" + 
			"FROM LibraryCardHolder L, LibraryCardHolder_InventoryItem LCH, InventoryItem II, Album A "
			+ "WHERE L.CardNumber == LCH.LibraryCardNumber and LCH.InventoryItemNumber == II.ItemID "
			+ "and II.MediaName == A.MediaName and LCH.LibraryCardNumber = ?";
	private String GET_MAX_CHECKOUT_PATRON = "SELECT FirstName, LastName, Count(*) "
			+ "FROM LibraryCardHolder as L, LibraryCardHolder_InventoryItem as LCH, Inventory Item as II, Movie as M "
			+ "WHERE L.CardNumber == LCH.LibraryCardNumber and LCH.InventoryItem == II.ItemID and II.MediaName 	== M.MediaName HAVING Max(M.MediaName) ";
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
    	PreparedStatement ps1;
    	ResultSet rs;
		try {
			ps1 = conn.prepareStatement(GET_TRACKS_FROM_ARTIST_BEFORE_YEAR);
			ps1.setInt(1, Integer.parseInt(year));
			ps1.setString(2, artistName);
			rs = ps1.executeQuery();
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
		PreparedStatement st;
		try {
			st = conn.prepareStatement(INSERT_ARTIST);
			st.setString(1, artistName);
			st.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void addNewTrack(String trackName, String albumName, int beatsPerMinute)  {
		Connection conn = DatabaseManager.initializeDB();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT_TRACK);
			ps.setString(1, trackName);
			ps.setString(2, albumName);
			ps.setInt(3, beatsPerMinute);
			ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
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
		PreparedStatement ps1;
		PreparedStatement ps2;
		try {
			ps1 = conn.prepareStatement(INSERT_ORDER);
			ps1.setInt(1, orderNum);
			ps1.setDate(2, new java.sql.Date(estimatedArrival.getTime()));
			ps1.executeUpdate();
			
			ps2 = conn.prepareStatement(INSERT_ORDERITEM);
			ps2.setString(1, mediaName);
			ps2.setDouble(2, price);
			ps2.setInt(3, quantity);
			ps2.setInt(4, orderNum);
			ps2.executeUpdate();
			JOptionPane.showMessageDialog(null, "Movie has been ordered.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		PreparedStatement ps1;
		try {
			ps1 = conn.prepareStatement(UPDATE_ARTIST);
			ps1.setString(1, updatedName);
			ps1.setString(2, originalName);
			ps1.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Updated artist successfully.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activateItemReceived() {
		// add item to inventory
		
		// remove item from order items
	}
	
	public int numAlbumsCheckedOutByPatron(int libraryCardNumber) {
    	Connection conn = DatabaseManager.initializeDB();
    	int numCheckouts = 0;
    	PreparedStatement ps;
    	ResultSet rs;
    	try {
			ps = conn.prepareStatement(GET_ALBUMS_CHECKED_OUT_BY_PATRON);
			ps.setInt(1, libraryCardNumber);
			rs = ps.executeQuery();
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
