package rvt.BoxInterface;

public class Book implements Packable {
    String author;
    String name;
    double weight;

    public Book(String author, String name, double weight) {
        this.author = author;
        this.name = name;
        this.weight = weight;

    }

    @Override
    public String toString() {
        return author + ": " + name;
    }

    @Override
    public double weight() {
        return weight;
    }
}
