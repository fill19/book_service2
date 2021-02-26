package cursor.book_service2;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library/books")
public class LibraryController {

    @Autowired
    RestTemplate restTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public List<Books> getAllBooks() {
        List<?> books = restTemplate.getForObject("http://library/books", List.class);
        assert books != null;
        return books.stream().map(o -> objectMapper.convertValue(o, Books.class))
                .collect(Collectors.toList());


    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://library/books/" + id, Book.class);
    }

    @GetMapping("/library-client/read/{id}")
    public String completeTitle(@PathVariable("id") Long id) {
        Books books = restTemplate.getForObject("http://library/read/" + id, Books.class);
        return books.getName();

    }
}
