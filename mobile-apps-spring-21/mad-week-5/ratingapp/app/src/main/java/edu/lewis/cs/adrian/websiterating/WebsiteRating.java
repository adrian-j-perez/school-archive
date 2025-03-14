package edu.lewis.cs.adrian.websiterating;

/**
 * BY  adrian perez
 * this class will hold user data
 * related to the website they visited
 * */

public class WebsiteRating {
    private String websiteName;
    private String reasonForUse;
    private String wouldYou;
    private int starsRatingNumber;

    //default con
    public WebsiteRating() {
        websiteName = "";
        reasonForUse = "";
        wouldYou = "";
        starsRatingNumber = 0;
    }

    //getter and setters
    public String getWebsiteName() {
        return websiteName;
    }
    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getReasonForUse() {
        return reasonForUse;
    }

    public void setReasonForUse(String reasonForUse) {
        this.reasonForUse = reasonForUse;
    }

    public String getWouldYou() {
        return wouldYou;
    }

    public void setWouldYou(String wouldYou) {
        this.wouldYou = wouldYou;
    }

    public int getStarsRatingNumber() {
        return starsRatingNumber;
    }

    public void setStarsRatingNumber(int starsRatingNumber) {
        this.starsRatingNumber = starsRatingNumber;
    }

    @Override
    public String toString() {
        return  "WebsiteRating\n" +
                "websiteName: " + websiteName + "\n" +
                "reasonForUse:" + reasonForUse + "\n" +
                "wouldYou: " + wouldYou + "\n"+
                "star: " + starsRatingNumber;
    }
}//end of class
