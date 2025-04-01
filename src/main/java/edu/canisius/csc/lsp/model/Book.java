package edu.canisius.csc.lsp.model;

public class Book {
    public String id;
    public String title;
    public String author;
    public String review;
    public double[] embedding;

    public String toString() {
        return title + " by " + author;
    }
}
