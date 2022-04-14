// interface (implemented with proposal)
  
interface SongDataInterface {
    public String getTitle();
    public String getArtist();
    public int getYearPublished();
}

// public class (implemented primarilly in final app week)

public class SongData implements SongDataInterface {

    private String title;
        private String artist;
        private int year;

        public void setTitle(String title)
        {
                this.title = title;
        }

        public void setArtist(String artist)
        {
                this.artist = artist;
        }

        public void setYearPublished(int year)
        {
                this.year = year;
        }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return this.title;
    }
    @Override
    public String getArtist() {
        // TODO Auto-generated method stub
        return this.artist;
    }
    @Override
    public int getYearPublished() {
        // TODO Auto-generated method stub
        return this.year;
    }

}


class SongDataPlaceholderA implements SongDataInterface {
    public String getTitle() { return "Song A Vowel"; }
    public String getArtist() { return "Artist X"; }
    public int getYearPublished() { return 1900; }
}
class SongDataPlaceholderB implements SongDataInterface {
    public String getTitle() { return "Song B Consonant"; }
    public String getArtist() { return "Artist Y"; }
    public int getYearPublished() { return 2000; }
}
class SongDataPlaceholderC implements SongDataInterface {
  public String getTitle() { return "Song C Consonant"; }
  public String getArtist() { return "Artist X"; }
  public int getYearPublished() { return 2021; }
}