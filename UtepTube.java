import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class UtepTube {
  public static void main(String[] args)throws FileNotFoundException{
    Scanner scanner = new Scanner(System.in);
    File corpus = new File("corpus.csv");
    Scanner fileReader = new Scanner(corpus);
    String playlist = "------------ YOUR PLAYLIST ------------\n"; 
    int count = 1;
    boolean menu = true;
    int totalSeconds = 0;
    int totalMinutes = 0;
    int totalHours = 0;
    String playlistSeconds = " ";
    String playlistMinutes = " ";
    String totalTime = "";
    while(menu){
      String menu1 = ("Welcome to UtepTube! Please select an option below to continue: \n" + 1 + " " + "List videos in corpus \n" + 2 + " " + "Add video to playlist \n" + 3 + " " + "View playlist \n" + 4 + " " + "Clear playlist \n" + 5 + " " + "Exit UtepTube \n" );
      System.out.print(menu1);
      int n = scanner.nextInt();
    
      if(n == 1){
        while(fileReader.hasNextLine()){
          String line = fileReader.nextLine();
          Scanner lineReader = new Scanner(line);
          lineReader.useDelimiter(",");
          String videoId = lineReader.next();
          String videoTitle = lineReader.next();
          String creator = lineReader.next();
          String minutes = lineReader.next();
          String seconds = lineReader.next();
          String newLine = (videoId + "|" + videoTitle + "|" + creator + "|" + minutes + "|" + seconds + "|");
          System.out.println(newLine);
          lineReader.close();
        }
      }
        if (n == 2){
          String isPreRoll = " ";
          String isMidroll = " ";
          String isPostroll = " ";
          System.out.println("Enter your VideoID");
          String inputID = scanner.next();
          fileReader = new Scanner(corpus);
          while(fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            Scanner lineReader = new Scanner(line);
            lineReader.useDelimiter(",");
            String videoId = lineReader.next();
            if(videoId.equals(inputID)){
              String videoTitle = lineReader.next();
              String creator = lineReader.next();
              String minutes = lineReader.next();
              String seconds = lineReader.next();
              String preRoll = lineReader.next();
              String midRoll = lineReader.next();
              String postRoll = lineReader.next();
              if(Boolean.parseBoolean(preRoll)){
                isPreRoll += "+30s preroll";
                totalSeconds += 30;
              }
              if (Boolean.parseBoolean(midRoll)){
                System.out.print("Would you like a 10s or 2m ad? type true/false\n");
                boolean skiP = scanner.nextBoolean();
                if(skiP){
                  totalSeconds += 10;
                  isMidroll += "+10s Midroll";
                }else if(!skiP){
                  isMidroll += "+2m Midroll";
                  totalMinutes += 2;
                }       
              }
              if(Boolean.parseBoolean(postRoll)){
                isPostroll += ("+5s Postroll");
                totalSeconds += 5;
              }
              Integer.parseInt(minutes);
              Integer.parseInt(seconds);
              totalMinutes += Integer.parseInt(minutes);
              totalSeconds += Integer.parseInt(seconds);

                while(totalSeconds > 60){
                  totalSeconds -= 60;
                  totalMinutes++;
                }
                while(totalMinutes > 60){
                  totalMinutes -= 60;
                  totalHours++;
                }

              if(Boolean.parseBoolean(preRoll)){
                playlist += count + " " + "https://youtu.be" + videoId + "|" + minutes + ":" + seconds + "(" + isPreRoll + " " + isMidroll + " " + isPostroll + ")\n";

              }else if(!(Boolean.parseBoolean(preRoll)) && !(Boolean.parseBoolean(midRoll)) && !(Boolean.parseBoolean(postRoll))){
                playlist += count + " " + "https://youtu.be" + videoId + "|" + minutes + ":" + seconds + "(no ads)\n";

              }
              String newLine2 = "Excellent Choice!\n"+videoTitle+"\n"+creator+"\n";
              count++;
              System.out.print(newLine2);
              lineReader.close();
            }
          }
        }
          if(n == 3){
            playlistSeconds = "";
            playlistMinutes = "";
            System.out.print(playlist);
            if(totalSeconds < 10){
              playlistSeconds = "0" + totalSeconds;
            }
            else{
              playlistSeconds += totalSeconds;
            }
            if(totalMinutes < 10){
              playlistMinutes = "0" + totalMinutes;
            }
            else{
              playlistMinutes += totalMinutes;
            }
            totalTime = ("Total Play Time:" + " " + totalHours + ":" + playlistMinutes + ":" + playlistSeconds + "\n");
            System.out.print(totalTime);
        
        }
        if(n == 4){
          playlistSeconds = "";
          playlistMinutes = "";
          System.out.print("Would you like to clear playlist? Type true/false: \n");
          boolean optOut = scanner.nextBoolean();
          if(optOut){
            playlist = "------------ YOUR PLAYLIST ------------\n";
            totalMinutes = 0;
            totalSeconds = 0;
            totalHours = 0;
            if(totalSeconds<10){
              playlistSeconds = "0" + totalSeconds;
            }
            if(totalMinutes<10){
              playlistMinutes = "0" + playlistMinutes;
            }
            totalTime +=   playlist + "Total play time:" + " " + totalHours + ":" + playlistMinutes + ":" + playlistSeconds + "\n";
            System.out.print(totalTime);
          }
          else if(!optOut){
            System.out.print("Taking you back to main menu..\n");
          }
        }
        if(n == 5){
          System.out.print("Hope to see you again!");
          menu = false;
        }
     }
    }
  }