package edu.canisius.csc.lsp.service;

import edu.canisius.csc.lsp.model.Book;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarityService {
    public List<Book> findTop3Similar(Book target, List<Book> allBooks) {
        return allBooks.stream()
                .filter(book -> !book.id.equals(target.id))
                .sorted(Comparator.comparingDouble(book -> -cosineSimilarity(target.embedding, book.embedding)))
                .limit(3)
                .collect(Collectors.toList());
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0, normA = 0, normB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
