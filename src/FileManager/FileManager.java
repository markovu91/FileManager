
package FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("Unesite komandu (list, info, create_dir, rename, copy, move, delete): ");
        switch (scanner.nextLine()) {
            
            // Pregled fajlova i foldera
            case "list":
                
                System.out.println("Uneli ste komandu list");
                System.out.println("Unesite lokaciju foldera: ");
        
                File path = new File (scanner.nextLine());
                if (path.exists() && path.isDirectory()) {
                String[] strings = path.list();
                for (String string : strings) {
                System.out.println(string);
                }
                }
                else {System.out.println("Folder ne postoji!");}

                break;
            
            //Prikaz informacija o fajlovima/folderima    
            case "info":
                
                System.out.println("Uneli ste komandu info");
                System.out.println("Unesite putanju do fajla/foldera: ");
                String putanja = scanner.nextLine();
                File file = new File (putanja);

                if(file.exists() && file.isFile()) {
                    System.out.println("Trazi se fajl");
                if (putanja.startsWith("C:") || putanja.startsWith("D:")) {
                    File absolute = new File(putanja);
                    BasicFileAttributes created = Files.readAttributes(absolute.toPath(), BasicFileAttributes.class);
                    Instant instant = Instant.ofEpochMilli(absolute.lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
            
                    System.out.println("kreiran:" + created.creationTime());
                    System.out.println("apsolutna putanja:" + absolute.getAbsolutePath());
                    System.out.println("naziv fajla:" + absolute.getName());
                    System.out.println("velicina fajla je:" + absolute.length() + " bajta");
                    System.out.println("datum poslednje izmene:" + dateTime.format(dateTimeFormatter));
                
            } 
                    // Relativna putanja do fajla
                    else {
                    File relative = new File (putanja);
                    BasicFileAttributes created = Files.readAttributes(relative.toPath(), BasicFileAttributes.class);
                    Instant instant = Instant.ofEpochMilli(relative.lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
            
            
            
                    System.out.println("kreiran: " + created.creationTime());
                    System.out.println("apsolutna putanja: " + relative.getAbsolutePath());
                    System.out.println("naziv fajla: " + relative.getName());
                    System.out.println("velicina fajla je: " + relative.length() + " bajta");
                    System.out.println("datum poslednje izmene: " + dateTime.format(dateTimeFormatter));
            
            }
            
            
            } else {System.out.println("Fajl " + file.getName() + " ne postoji!");}
            
            
            
            
            if (file.exists() && file.isDirectory()) {
                System.out.println("Trazi se folder");
                
                if (putanja.startsWith("C:") || putanja.startsWith("D:")) {
                    File absolute = new File (putanja);
                    Long folderSize = findSize (putanja);
                    Instant instant = Instant.ofEpochMilli(absolute.lastModified());
                    LocalDateTime  dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                BasicFileAttributes created = Files.readAttributes(absolute.toPath(), BasicFileAttributes.class);
                
                    System.out.println("kreiran: " + created.creationTime());
                    System.out.println("apsolutna putanja: " + absolute.getAbsolutePath());
                    System.out.println("naziv foldera: " + absolute.getName());
                    System.out.println("velicina foldera je: " + folderSize + " bajta" );
                    System.out.println("datum poslednje izmene: " + dateTime.format(dateTimeFormatter));
                  
                }
               
                else {
                
                File relative = new File (putanja);
                Long folderSize = findSize (putanja);
                Instant instant = Instant.ofEpochMilli(file.lastModified());
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                BasicFileAttributes created = Files.readAttributes(relative.toPath(), BasicFileAttributes.class);
                 
                    System.out.println("kreiran: " + created.creationTime());
                    System.out.println("apsolutna putanja: " + relative.getAbsolutePath());
                    System.out.println("naziv foldera: " + relative.getName());
                    System.out.println("velicina foldera je: " + folderSize + " bajta" );
                    System.out.println("datum poslednje izmene: " + dateTime.format(dateTimeFormatter));
                
                }

            }
                break;
             
                // Kreiranje foldera
            case "create_dir":
                
                 System.out.println("Uneli ste komandu create_dir");
                System.out.println("Unesite lokaciju novog foldera: ");
               String dir = scanner.nextLine();
                File directory = new File (dir);
                try {
                    if(!directory.exists()) {
                        directory.mkdir();
                        System.out.println("Napravljen direktorijum sa nazivom: " + directory.getName());
                    }
                    else {
                        System.out.println("Direktorijum sa nazivom: " + directory.getName() + " vec postoji.");
                    }
                }
                        catch (Exception e) {
                                
                                System.out.println("Nije moguce napraviti direktorijum sa nazivom: " + directory.getName());
                                }
                break;
                
                // Promena imena fajlova/foldera
                case "rename":
                    
                    System.out.println("Uneli ste komandu rename");
                    System.out.println("Unesite naziv foldera/fajla sa putanjom kojeg zelite da izmenite: ");
                    File oldfolder = new File (scanner.nextLine());
                    System.out.println("Unesite naziv novog foldera/fajla sa putanjom: ");
                    File newfolder = new File (scanner.nextLine());
                    if(!oldfolder.exists()) {
                        System.out.println("Folder " + oldfolder.getName() + " ne postoji!");
                        return;

                    }
                    if(newfolder.exists()) {
                        System.out.println("Folder sa tim nazivom vec postoji!");
                        return;
                    }
                    if(oldfolder.renameTo(newfolder)) {
                        System.out.println("Promena naziva uspesna!");
                    
                    }
                    else {
                        System.out.println("Promena naziva neuspesna!");
                    }
                
                break;
                
                // Kopiranje fajlova i foldera
                case "copy":
                    
                    System.out.println("Uneli ste komandu copy");
                    System.out.println("Unesite putanju fajla/foldera koji zelite da kopirate: ");
                    File srcFolder = new File (scanner.nextLine());
                    System.out.println("Unesite putanju gde zelite da kopirate: ");
                    File destFolder = new File (scanner.nextLine());
                    
                    if (!srcFolder.exists()) {
                        System.out.println("Direktorijum " + srcFolder.getName() + " ne postoji!");
                    }
                    
                    else {
                    
                        try {
                      copyFolder(srcFolder, destFolder);
                        } catch (IOException e ) {System.out.println(e);}
                        
                    }
                   
                   
                break;
                
                
                // Brisanje fajlova/foldera
                case "delete":
                    
                     System.out.println("Uneli ste komandu delete");
                    System.out.println("Unesite putanju foldera\fajla koji zelite da obrisete");
                    File filed = new File (scanner.nextLine());
                    if (filed.exists()) {
                    filed.delete();
                    deleteDir(filed);
                        System.out.println("Fajl/Folder " + filed.getName() + " je uspesno obrisan!");
                    
                    }
                    else {
                        System.out.println("Ne moze da se obrise " + filed.getName()+ " zbog toga sto " + filed.getName() + " ne postoji!");
                    
                    }
     
                    break;
                
                // Premestanje fajlova/foldera
                case "move":
                    
                    System.out.println("Uneli ste komandu move");
                    String put= "";
                    String destinacija = "";
                    try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                        System.out.println("Unesite putanju fajla/foldera:");
                        put = bufferRead.readLine();
                        
                        System.out.println("Unesite odredisnu lokaciju fajla\foldera:");
                        destinacija = bufferRead.readLine();

                    }
                    catch (IOException e) {
                        System.out.println(e);
                    
                    }
                    
                    File filea = new File(put);
                    File fileb = new File(destinacija + "\\" + filea.getName());
                   if (filea.isDirectory() && filea.exists()) {
                       try{
                   moveFolder(filea, fileb);
                   deleteDir(filea);
                       } catch (IOException e) {System.out.println(e);}
                   }
                    
                   else {System.out.println("Direktorijum sa nazivom " + filea.getName() + " ne postoji!");}
                    
                    
                    
                    try (FileInputStream inStream = new FileInputStream(filea);
                            FileOutputStream outStream = new FileOutputStream(fileb)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inStream.read(buffer)) > 0) {
                            outStream.write(buffer, 0, length);
                        }
                        System.out.println("fajl/folder " + filea.getName() + " je uspesno premesten!");
                        inStream.close();
                        outStream.close();
                        filea.delete();
                    } catch (IOException exc) {

                        System.out.println(exc);
                    }
                    
                    break;
                    
                default: 
                    System.out.println("Niste dobro uneli komandu!");
                    
                    


                            }
                        }
    
     // Metoda za brisanje fajlova i foldera
   public static void deleteDir(File filed) {
        File[] contents = filed.listFiles();
        if (contents != null) {
            for (File f : contents) {
                    deleteDir(f);
                                }
                            }
                            filed.delete();
                        }
 
    
    // Metoda za kopiranje foldera
    public static void copyFolder (File src, File dest) throws IOException {
    
    if (src.isDirectory()) {
    
        if (!dest.exists()) {
        
           dest.mkdir();
            System.out.println("Direktorijum sa nazivom " + src.getParent() + " je kopiran iz " + src + " u putanju " + dest);
        }
        
        String [] files = src.list();
        
       for (String file : files) {
       File srcFile = new File (src, file);
       File destFile = new File (dest, file);
       
       copyFolder (srcFile, destFile);
       }
    }
       else {
               
               try (FileInputStream inStream = new FileInputStream(src);
                    FileOutputStream outStream = new FileOutputStream(dest))
                    {
                    byte [] buffer = new byte [1024];
                    int length;
                    while ((length=inStream.read(buffer))>0)
                    {
                        outStream.write(buffer, 0, length);
                    }
                        System.out.println("Fajl " + src.getName() + " je uspesno kopiran!");
                    }
                    catch(IOException exc)
                    {
                        System.out.println(exc);
                    
                    }
               }
       
       
    }
    
    
    // Metoda za premestanje foldera
    public static void moveFolder (File filea, File fileb) throws IOException {
    
    if (filea.isDirectory()) {
    
        if (!fileb.exists()) {
        
           fileb.mkdir();
            System.out.println("Direktorijum sa nazivom " + filea.getName() + " je premesten iz " + filea + " u putanju " + fileb);
        }
        
        String [] files = filea.list();
        
       for (String file : files) {
       File srcFile = new File (filea, file);
       File destFile = new File (fileb, file);
       
       moveFolder (srcFile, destFile);
       }
    }
       else {
               
               try (FileInputStream inStream = new FileInputStream(filea);
                    FileOutputStream outStream = new FileOutputStream(fileb))
                    {
                    byte [] buffer = new byte [1024];
                    int length;
                    while ((length=inStream.read(buffer))>0)
                    {
                        outStream.write(buffer, 0, length);
                    }
                        System.out.println("Fajl " + filea.getName() + " je uspesno premesten!");
                    }
                    catch(IOException exc)
                    {
                        System.out.println(exc);
                    
                    }
               }
       
       
    }
    
    //Metoda za odredjivanje velicine foldera
    public static long findSize(String path)
    {
        long totalSize = 0;
        ArrayList<String>  directory = new ArrayList<String>();
        File file = new File(path);
        if(file.isDirectory())
        {
            directory.add(file.getAbsolutePath());
            while (directory.size() > 0) {
                String folderPath = directory.get(0);              
                directory.remove(0);
                File folder = new File(folderPath);
                File[] filesInFolder = folder.listFiles();
                int noOfFiles = filesInFolder.length;
                for(int i = 0 ; i < noOfFiles ; i++)
                {
                    File f = filesInFolder[i];
                    if(f.isDirectory())
                    {
                        directory.add(f.getAbsolutePath());
                    }else
                    {
                        totalSize+=f.length();
                    }
                }
            }
        }else
        {
            totalSize = file.length();
        }
        return totalSize;
    }
    
    

    }

                   
                   
                   
                   
                   
                   
                   
    
            


                
                
       
     
    

