import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

// interface (implemented with proposal)

interface SearchBackEndInterface 
{    
    public void addSong(SongDataInterface song);
    public boolean containsSong(SongDataInterface song);

    // returns list of the titles of all songs that contain the word titleWord in their song title
    public List<String> findTitles(String titleWord);

    // returns list of the artists of all songs that contain the word titleWord in their song title
    public List<String> findArtists(String titleWord);

    // returns the number of songs that contain the word titleWord in their song title, and were published in year
    public int findNumberOfSongsInYear(String titleWord, int year);
}

// public class (implemented primarilly in final app week)

public class SearchBackEnd implements SearchBackEndInterface 
{
    static private class ListNode
    {
      Object key;
      List<String> title;
      List<String> artists;
      List<Integer> year;
      ListNode pointer; // pointer to next node in list
    }
    
    private ListNode[] table; // hash table
    
    @Override
    public void addSong(SongDataInterface song)
    {
        // TODO Auto-generated method stub
        boolean exists = false;
        String str = song.getTitle();
        String [] arrOfStr = str.split(" ");
        for(int i = 0; i < arrOfStr.length; i++)
        {
          int bucket = hash(arrOfStr[i]);
          ListNode list = table[bucket];

          while(list != null)
          {
//            if(list.key.equals(arrOfStr[i])) // Determine if a word exists in the table
//            {
              for(String j : list.title)
              {
                if(j.equals(song.getTitle()))
                {
                  exists = true;
                  break;
                }
              }
              if(!exists)
              {
                list.title.add(song.getTitle());
                list.year.add(song.getYearPublished());
                list.artists.add(song.getArtist());
              }
//            }
            
            list = list.pointer;
          }
          if(list != null)
          {
            break;
          }
          else
          {
            ListNode newNode = new ListNode();
            newNode.key = arrOfStr[i];
            newNode.title = new ArrayList <String>();
            newNode.title.add(song.getTitle());
            
            newNode.year = new ArrayList <Integer>();
            newNode.year.add(song.getYearPublished());
            
            newNode.artists = new ArrayList<String>();
            newNode.artists.add(song.getArtist());
            
            newNode.pointer = table[bucket];
            table[bucket] = newNode;
          }
        }       
    }

    @Override
    public boolean containsSong(SongDataInterface song) 
    {
        // TODO Auto-generated method stub
      String str = song.getTitle();
      String [] arrOfStr = str.split(" ");
      int bucket = hash(arrOfStr[0]); // Location of key
      ListNode list = table[bucket];
      while(list != null) // Checks if key is in node that table points to. If yes, returns the vlaue
      {
        if(list.key.equals(arrOfStr[0]))
        {          
          for(String i : list.title)
          {
            if(i == song.getTitle())
            {
              return true;
            }
          }
        }
        list = list.pointer; // Move to next node
      }
      return false;
    }

    @Override
    public List<String> findTitles(String titleWord) throws NoSuchElementException
    {
        // TODO Auto-generated method stub
      int bucket = hash(titleWord); // Location of key
      ListNode list = table[bucket];
      
      while(list != null) // Checks if key is in node that table points to. If yes, returns the vlaue
      {
        if(list.key.equals(titleWord))
        {
          return list.title;
        }
        list = list.pointer; // Move to next node
      }
      throw new NoSuchElementException("Element not in table"); 

    }

    @Override
    public List<String> findArtists(String titleWord) 
    {
        // TODO Auto-generated method stub
      int bucket = hash(titleWord); // Location of key
      ListNode list = table[bucket];
      
      while(list != null) // Checks if key is in node that table points to. If yes, returns the vlaue
      {
        if(list.key.equals(titleWord))
        {
          return list.artists;
        }
        list = list.pointer; // Move to next node
      }
      throw new NoSuchElementException("Element not in table"); 

    }

    @Override
    public int findNumberOfSongsInYear(String titleWord, int year) 
    {
        // TODO Auto-generated method stub
      int bucket = hash(titleWord); // Location of key
      ListNode list = table[bucket];
      int count = 0;
      while(list != null) // Checks if key is in node that table points to. If yes, returns the vlaue
      {
        if(list.key.equals(titleWord))
        {
          //Integer [] arr = list.year.toArray(new Integer[list.year.size()]);
          
          for(int i : list.year)
          {
            if(i == year)
            {
              count++;
            }
          }
          return count;
        }
        list = list.pointer; // Move to next node
      }
      throw new NoSuchElementException("Element not in table"); 

    }
    private int hash(Object key) // Computes hash code for a given key. 
    {
      return (Math.abs(key.hashCode())) % table.length;
    }
    public boolean containsKey(Object key)  // Checks whether the given key is in the table or not.
    {
      int bucket = hash(key);
      ListNode list = table[bucket];
      while(list != null) // If key is found, return true.
      {
        if(list.key.equals(key))
        {
          return true;
        }
        list = list.pointer;
      }
      
      return false; // No key was found in the table, return false.
    }
}

// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchBackEndPlaceholder implements SearchBackEndInterface
{
  private SongDataInterface onlySong;

  public void addSong(SongDataInterface song) 
  {
      this.onlySong = song;
  }
  public boolean containsSong(SongDataInterface song) 
  {
      return onlySong.equals(song);
  }
  public List<String> findTitles(String titleWord) 
  {
      List<String> titles = new LinkedList<>();
      if(onlySong.getTitle().contains(titleWord))
          titles.add(onlySong.getTitle());
      return titles;
  }
  public List<String> findArtists(String titleWord)
  {
      List<String> artists = new LinkedList<>();
      if(onlySong.getArtist().contains(titleWord))
          artists.add(onlySong.getArtist());
      return artists;
  }
  public int findNumberOfSongsInYear(String titleWord, int year) 
  {
      if(onlySong.getYearPublished() == year) return 1;
      return 0;
  }
  
}
