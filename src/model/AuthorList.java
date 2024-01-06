package model;

import model.Utility.FilePrinterUtil;
import model.Utility.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthorList {
    private List<Author> authors;
    public AuthorList(){
        authors = new ArrayList<>();
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void add(Author author) {
        authors.add(author);
    }
}
