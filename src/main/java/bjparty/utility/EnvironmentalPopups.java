package bjparty.utility;

public class EnvironmentalPopups {
    private String content; //the message which will be displayed in the popup
    private long randonTimer; // a variable which stores a random variable 
    
    public EnvironmentalPopups(String content, long randonTimer) {
        this.content = content;
        this.randonTimer = randonTimer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getRandonTimer() {
        return randonTimer;
    }

    public void setRandonTimer(long randonTimer) {
        this.randonTimer = randonTimer;
    }

    public void displayPopup()
    {
        //this function will display a message dialog box which will contain the popup msg (content) and will
        //be displayed at a "random" time (randomTimer)
    }
}
