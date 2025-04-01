package edu.canisius.csc.lsp.controller;

import edu.canisius.csc.lsp.model.Book;
import edu.canisius.csc.lsp.service.SimilarityService;
import edu.canisius.csc.lsp.storage.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private SimilarityService similarity;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String id, Model model) {
        List<Book> allBooks = repository.getAll();
        model.addAttribute("books", allBooks);

        if (id != null) {
            Book selected = repository.getById(id);
            List<Book> similar = similarity.findTop3Similar(selected, allBooks);

            model.addAttribute("selected", selected);
            model.addAttribute("similar", similar);
        }

        return "index";
    }
}
