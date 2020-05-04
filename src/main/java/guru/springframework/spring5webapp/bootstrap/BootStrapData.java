package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author erik = new Author("Erik", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        erik.getBooks().add(ddd);
        ddd.getAuthors().add(erik);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "35465");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        Publisher pub = new Publisher("Publisher Example", "Rua Los Angeles 12 3E", "Pontevedra", "Spain", "36002");
        ddd.setPublisher(pub);
        noEJB.setPublisher(pub);
        pub.getBooks().addAll(Arrays.asList(ddd, noEJB));

        authorRepository.save(erik);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pub);

        System.out.println("Started in BootStrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher num of books: " + pub.getBooks().size());
        System.out.println("Started in BootStrap");
    }
}
