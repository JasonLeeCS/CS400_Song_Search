import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// interface (implemented with proposal)
interface SongLoaderInterface {
        public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;
        
        public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
                        throws FileNotFoundException;
}

// public class (implemented primarilly in final app week)
public class SongLoader implements SongLoaderInterface {

        public List<String> ls1 = new ArrayList<>();

        @Override
        //<SongDataInterface>
        public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
                // TODO Auto-generated method stub
                String line = "";
                int title = -1;
                int artist = -1;
                int year = -1;
                List<SongDataInterface> ls = new ArrayList<SongDataInterface>();

                try
                {
                        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));

                        if((line = br.readLine()) != null)
                        {
                                String[] line1 = line.split(",");
                                for(int i = 0; i < line1.length; i++)
                                {
                                        if(line1[i].equals("\"title\"") || line1[i].equals("title"))
                                        {
                                                title = i;
                                        }
                                        else if(line1[i].equals("\"artist\"") || line1[i].equals("artist"))
                                        {
                                                artist = i;
                                        }
                                        else if(line1[i].equals("\"year\"") || line1[i].equals("released"))
                                        {
                                                year = i;
                                        }
                                }
                        }

                        if(title == -1 || artist == -1 || year == -1)
                        {
                                throw new FileNotFoundException("CSV file not accepted type");
                        }


                        while((line = br.readLine()) != null)
                        {
                                String[] vals = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                                //System.out.println(vals[title] + " SPACE " + vals[artist] + " SPACE " + vals[year]);
                                SongData val = new SongData();
                                val.setTitle(vals[title].replaceAll("\"", ""));
                                val.setArtist(vals[artist].replaceAll("\"", ""));
                                String yearStr = vals[year].replaceAll("\"", "");
                                int yearPub = Integer.parseInt(yearStr.substring(yearStr.length() - 4, yearStr.length()));
                                val.setYearPublished(yearPub);
                                ls.add(val);
                        }

                        br.close();
                }
                catch(FileNotFoundException e)
                {
                        e.printStackTrace();
                }
                catch (IOException e2)
                {
                        e2.printStackTrace();
                }

                return ls;
        }


        @Override
        public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
                        throws FileNotFoundException {
                // TODO Auto-generated method stub
                File folder = new File(directoryPath);
                File[] list = folder.listFiles();
                List<SongDataInterface> ls = new ArrayList<SongDataInterface>();

                for(File f: list)
                {
                        if(f.isFile())
                        {
                                List<SongDataInterface> temp = new ArrayList<SongDataInterface>();
                                temp = loadFile(f.getAbsolutePath());
                                //System.out.println(temp.size());
                                ls.addAll(temp);
                        }
                }
                return ls;
        }





}

class SongLoaderPlaceholder implements SongLoaderInterface {
        public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
                List<SongDataInterface> list = new ArrayList<>(); list.add(new SongDataPlaceholderA());
                list.add(new SongDataPlaceholderB());
                return list;
        }

        public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
                        throws FileNotFoundException {
                List<SongDataInterface> list = new ArrayList<>();
                list.add(new SongDataPlaceholderA());
                list.add(new SongDataPlaceholderB());
                list.add(new SongDataPlaceholderC());
                return list;
        }
}