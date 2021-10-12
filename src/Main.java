import java.util.ArrayList;
import java.util.List;
import javax.swing.*;  

public class Main {
//	List<Track> trackList;
//	List<Artist> artistList;
//	List<Artist_Track> artistTrackList;

	public static void main(String[] args) {
		
		JFrame mainFrame = new MainFrame();
		
		DataStorageService service = DataStorageService.getInstance();
		
		System.out.println("Tracks with artists containing 'Rolling': " + service.searchTracksByArtist("Rolling").get(0).mediaName);
		System.out.println("Tracks with titles containing 'Gimme': " + service.searchTracksByName("Gimme").get(0).mediaName);
	}

}
