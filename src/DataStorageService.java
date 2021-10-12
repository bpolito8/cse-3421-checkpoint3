import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class DataStorageService {
	private static DataStorageService service = new DataStorageService();
	List<Artist_Track> artistTrackList;
	List<Track> trackList;
	List<Artist> artistList;
	List<Movie> movieList;
	
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
		
		movieList = new ArrayList<Movie>();
		movieList.add(new Movie("The Matrix"));
		movieList.add(new Movie("Titanic"));
		movieList.add(new Movie("Pulp Fiction"));
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
		List<Track> results = new ArrayList<Track>();
		for(int i = 0; i < trackList.size(); i++) {
			if(trackList.get(i).mediaName.contains(trackName)) {
				results.add(trackList.get(i));
			}
		}
		return results;
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
		List<Artist> results = new ArrayList<Artist>();
		for(int i = 0; i < artistList.size(); i++) {
			if(artistList.get(i).name.contains(artistName)) {
				results.add(artistList.get(i));
			}
		}
		return results;
	}
	
	public List<Movie> getMovies(){
		return movieList;
	}
	
	public void orderMovie(Media media, int quantity, double price) {
		OrderItem orderItem = new OrderItem(media, quantity, price);
		Order order = new Order(new Date());
		order.addOrderItem(orderItem);
		JOptionPane.showMessageDialog(null, "Movie has been ordered.");
	}
}
