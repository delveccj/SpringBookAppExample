package edu.canisius.csc.lsp.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.canisius.csc.lsp.model.Book;
import jakarta.annotation.PostConstruct; // ✅ <--- Updated import
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> books;

    @PostConstruct
    public void load() throws Exception {
        InputStream is = getClass().getResourceAsStream("/books.json");
        ObjectMapper mapper = new ObjectMapper();
        books = mapper.readValue(is, new TypeReference<List<Book>>() {});
    }

    public List<Book> getAll() {
        return books;
    }

    public Book getById(String id) {
        return books.stream().filter(b -> b.id.equals(id)).findFirst().orElse(null);
    }
}
