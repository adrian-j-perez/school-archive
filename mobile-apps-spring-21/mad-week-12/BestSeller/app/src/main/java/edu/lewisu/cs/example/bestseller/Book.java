package edu.lewisu.cs.example.bestseller;

import android.os.Parcel;
import android.os.Parcelable;


class Book implements Parcelable {
    private String title;
    private String author;
    private String amazon;
    private String description;
    private int rank;


    public Book(String title, String author, String amazon, String description, int rank) {
        this.title = title;
        this.author = author;
        this.amazon = amazon;
        this.description = description;
        this.rank = rank;
    }

    public Book(String title) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAmazon() {
        return amazon;
    }

    public void setAmazon(String amazon) {
        this.amazon = amazon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        amazon = in.readString();
        description = in.readString();
        rank = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(amazon);
        dest.writeString(description);
        dest.writeInt(rank);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}