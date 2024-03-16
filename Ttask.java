import java.util.*;

class Song {
    String name;
    String singer;
    String time;
    String type;

    public Song(String name, String singer, String time, String type) {
        this.name = name;
        this.singer = singer;
        this.time = time;
        this.type = type;
    }
    static int i=0;

    @Override
    public String toString() {
        i++;
        
        return "Song no:"+i +" [name=" + name + ", singer=" + singer + ", time=" + time + ", type=" + type + "]";
    }
}

public class Ttask {

    public static void main(String[] args) {
        List<Song> songs = initializeSongs();
        Map<String, List<Song>> playlists = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Choose an option:");
                System.out.println("1. Display all songs");
                System.out.println("2. Search songs");
                System.out.println("3. Create a new playlist");
                System.out.println("4. Manage playlists");
                System.out.println("0. Exit");
                System.out.println("-----------------------------------------------------------------------");

                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        displaySongs(songs);
                        break;
                    case 2:
                        searchSongs(songs, sc);
                        break;
                    case 3:
                        createPlaylist(songs, playlists, sc);
                        break;
                    case 4:
                        managePlaylists(playlists, songs, sc);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println("Do you want to continue? (1: Yes, 0: No)");
                choice = sc.nextInt();
                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                sc.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static List<Song> initializeSongs() {
        List<Song> songs = new ArrayList<>();
        
        // Add sad songs
        songs.add(new Song("lag ja gale", "atharv", "3.47", "sad"));
        songs.add(new Song("rahat ali khan playlist", "atharv", "31.57", "sad"));
        songs.add(new Song("arijit Sing sad songs", "atharv", "13.47", "sad"));
        
        // Add happy songs
        songs.add(new Song("Sony nigam playlist", "atharv", "3.47", "happy"));
        songs.add(new Song("ajay atul ", "atharv", "31.57", "happy"));
        
        // Add workout songs
        songs.add(new Song("Hindi workout playlist", "prathamesh", "65.12", "workout"));
        songs.add(new Song("english workout playlist", "prathamesh", "65.12", "workout"));
        songs.add(new Song("hindi vs english workout playlist", "prathamesh", "65.12", "workout"));
        
        return songs;
    }
    

    private static void displaySongs(List<Song> songs) {
        System.out.println("All songs:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }

    private static void searchSongs(List<Song> songs, Scanner sc) {
        sc.nextLine(); // Consume newline
        System.out.println("Enter search keyword:");
        String keyword = sc.nextLine().toLowerCase();
    
        System.out.println("Search results:");
        for (Song song : songs) {
            if (song.name.toLowerCase().contains(keyword) ||
                song.singer.toLowerCase().contains(keyword) ||
                song.type.toLowerCase().contains(keyword)) {
                System.out.println(song);
            }
        }
    }
    

    private static void createPlaylist(List<Song> songs, Map<String, List<Song>> playlists, Scanner sc) {
        try {
            System.out.println("Enter the name of the new playlist:");
            String playlistName = sc.nextLine();
            playlists.put(playlistName, new ArrayList<>());
            System.out.println("New playlist created: " + playlistName);
        } catch (Exception e) {
            System.out.println("An error occurred while creating the playlist: " + e.getMessage());
        }
    }

    private static void managePlaylists(Map<String, List<Song>> playlists, List<Song> songs, Scanner sc) {
        while (true) {
            try {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Choose an option:");
                System.out.println("1. Create a new playlist");
                System.out.println("2. Display all playlists");
                System.out.println("3. Display songs in a playlist");
                System.out.println("4. Add songs to a playlist");
                System.out.println("5. Remove songs from a playlist");
                System.out.println("6. Rename a playlist");
                System.out.println("0. Go back to main menu");
                System.out.println("-----------------------------------------------------------------------");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        createNewPlaylist(playlists, sc);
                        break;
                    case 2:
                        displayAllPlaylists(playlists);
                        break;
                    case 3:
                        displayPlaylistSongs(playlists, sc);
                        break;
                    case 4:
                        addSongsToExistingPlaylist(playlists, songs, sc);
                        break;
                    case 5:
                        removeSongsFromPlaylist(playlists, sc);
                        break;
                    case 6:
                        renamePlaylist(playlists, sc);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                sc.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    private static void addSongsToExistingPlaylist(Map<String, List<Song>> playlists, List<Song> songs, Scanner sc) {
        try {
            System.out.println("Enter the name of the playlist to add songs:");
            String playlistName = sc.nextLine();
            List<Song> playlist = playlists.get(playlistName);
            if (playlist != null) {
                while (true) {
                    displaySongs(songs);
                    System.out.println("Choose song number to add to the playlist (or 0 to exit):");
                    int option = sc.nextInt();
                    if (option == 0) {
                        break;
                    }
                    if (option > 0 && option <= songs.size()) {
                        Song selectedSong = songs.get(option - 1);
                        if (!playlist.contains(selectedSong)) {
                            playlist.add(selectedSong);
                            System.out.println("Song added to the playlist.");
                        } else {
                            System.out.println("Song already exists in the playlist.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else {
                System.out.println("Playlist '" + playlistName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding songs to the playlist: " + e.getMessage());
        }
    }
    private static void createNewPlaylist(Map<String, List<Song>> playlists, Scanner sc) {
        try {
            System.out.println("Enter the name of the new playlist:");
            String playlistName = sc.nextLine();
            playlists.put(playlistName, new ArrayList<>());
            System.out.println("New playlist created: " + playlistName);
        } catch (Exception e) {
            System.out.println("An error occurred while creating the playlist: " + e.getMessage());
        }
    }

    private static void displayAllPlaylists(Map<String, List<Song>> playlists) {
        System.out.println("All playlists:");
        for (String playlistName : playlists.keySet()) {
            System.out.println(playlistName);
        }
    }

    private static void displayPlaylistSongs(Map<String, List<Song>> playlists, Scanner sc) {
        try {
            System.out.println("Enter the name of the playlist:");
            String playlistName = sc.nextLine();
            List<Song> playlist = playlists.get(playlistName);
            if (playlist != null) {
                System.out.println("Songs in playlist '" + playlistName + "':");
                for (int i = 0; i < playlist.size(); i++) {
                    System.out.println((i + 1) + ". " + playlist.get(i));
                }
            } else {
                System.out.println("Playlist '" + playlistName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the playlist: " + e.getMessage());
        }
    }

    private static void addSongsToPlaylist(Map<String, List<Song>> playlists, List<Song> songs, Scanner sc) {
        try {
            System.out.println("Enter the name of the playlist to add songs:");
            String playlistName = sc.nextLine();
            List<Song> playlist = playlists.get(playlistName);
            if (playlist != null) {
                while (true) {
                    displaySongs(songs);
                    System.out.println("Choose song number to add to the playlist (or 0 to exit):");
                    int option = sc.nextInt();
                    if (option == 0) {
                        break;
                    }
                    if (option > 0 && option <= songs.size()) {
                        Song selectedSong = songs.get(option - 1);
                        if (!playlist.contains(selectedSong)) {
                            playlist.add(selectedSong);
                            System.out.println("Song added to the playlist.");
                        } else {
                            System.out.println("Song already exists in the playlist.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else {
                System.out.println("Playlist '" + playlistName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding songs to the playlist: " + e.getMessage());
        }
    }
    

    private static void removeSongsFromPlaylist(Map<String, List<Song>> playlists, Scanner sc) {
        try {
            System.out.println("Enter the name of the playlist to remove songs:");
            String playlistName = sc.nextLine();
            List<Song> playlist = playlists.get(playlistName);
            if (playlist != null) {
                while (true) {
                    displayPlaylistSongs(playlists, sc);
                    System.out.println("Choose song number to remove from the playlist (or 0 to exit):");
                    int option = sc.nextInt();
                    if (option == 0) {
                        break;
                    }
                    if (option > 0 && option <= playlist.size()) {
                        playlist.remove(option - 1);
                        System.out.println("Song removed from the playlist.");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else {
                System.out.println("Playlist '" + playlistName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while removing songs from the playlist: " + e.getMessage());
        }
    }

    private static void renamePlaylist(Map<String, List<Song>> playlists, Scanner sc) {
        try {
            System.out.println("Enter the current name of the playlist:");
            String currentPlaylistName = sc.nextLine();
            System.out.println("Enter the new name of the playlist:");
            String newPlaylistName = sc.nextLine();
            List<Song> playlist = playlists.remove(currentPlaylistName);
            if (playlist != null) {
                playlists.put(newPlaylistName, playlist);
                System.out.println("Playlist renamed successfully.");
            } else {
                System.out.println("Playlist '" + currentPlaylistName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while renaming the playlist: " + e.getMessage());
        }
    }
}

