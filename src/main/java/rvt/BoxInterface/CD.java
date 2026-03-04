package rvt.BoxInterface;

public class CD implements Packable {
    String artist;
    String name;
    int publicationYear;
    Double weight;

    public CD(String artist, String name, int year) {
        this.artist = artist;
        this.name = name;
        this.weight = 0.1;
        this.publicationYear = year;

    }

    @Override
    public String toString() {
        return artist + ": " + name + " (" + publicationYear + ")";
    }

    @Override
    public double weight() {
        return weight;
    }
    
}
