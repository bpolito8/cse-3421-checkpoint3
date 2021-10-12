import java.util.ArrayList;
import java.util.List;

public class DataStorageService {
	private static DataStorageService service = new DataStorageService();
	List<Artist_Track> artistTrackList;
	List<Track> trackList;
	List<Artist> artistList;
	
	private DataStorageService() {
		// add dummy data
		trackList = new ArrayList<Track>();
		trackList.add(new Track("Gimme Shelter", "Let It Bleed", 100));
		
		artistList = new ArrayList<Artist>();
		artistList.add(new Artist("The Rolling Stones"));
		
		artistTrackList = new ArrayList<Artist_Track>();
		artistTrackList.add(new Artist_Track(artistList.get(0), trackList.get(0)));
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
	}
}
