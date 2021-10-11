import java.util.ArrayList;
import java.util.List;

public class Main {
//	List<Track> trackList;
//	List<Artist> artistList;
//	List<Artist_Track> artistTrackList;

	public static void main(String[] args) {
		// add dummy data
		List<Track> trackList = new ArrayList<Track>();
		trackList.add(new Track("Gimme Shelter", "Let It Bleed", 100));
		
		List<Artist> artistList = new ArrayList<Artist>();
		artistList.add(new Artist("The Rolling Stones"));
		
		List<Artist_Track> artistTrackList = new ArrayList<Artist_Track>();
		artistTrackList.add(new Artist_Track(artistList.get(0), trackList.get(0)));
		
		System.out.println("Tracks with artists containing 'Rolling': " + searchTracksByArtist("Rolling", artistTrackList).get(0).mediaName);
		System.out.println("Tracks with titles containing 'Gimme': " + searchTracksByName("Gimme", trackList).get(0).mediaName);
	}
	
	public static List<Track> searchTracksByArtist(String artistName, List<Artist_Track> artistTrackList){
		List<Track> results = new ArrayList<Track>();
		for(int i = 0; i < artistTrackList.size(); i++) {
			if(artistTrackList.get(i).artist.name.contains(artistName)) {
				results.add(artistTrackList.get(i).track);
			}
		}
		return results;
	}
	
	public static List<Track> searchTracksByName(String trackName, List<Track> trackList){
		List<Track> results = new ArrayList<Track>();
		for(int i = 0; i < trackList.size(); i++) {
			if(trackList.get(i).mediaName.contains(trackName)) {
				results.add(trackList.get(i));
			}
		}
		return results;
	}

}
