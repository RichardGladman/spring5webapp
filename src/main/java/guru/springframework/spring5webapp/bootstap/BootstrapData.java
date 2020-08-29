package guru.springframework.spring5webapp.bootstap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootstrapData(
			final AuthorRepository authorRepository, 
			final BookRepository bookRepository,
			final PublisherRepository publisherRepository
			) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Initialising data...");
		
		Publisher publisher = new Publisher();
		publisher.setName("SFG Publishing");
		publisher.setState("Kent");
		save(publisher);
		
		Author eric = new Author("Eric", "Evans");
		Book dddBook = new Book("Domain Driven Design", "12345");
		publisher.getBooks().add(dddBook);
		eric.getBooks().add(dddBook);
		
		dddBook.setPublisher(publisher);
		dddBook.getAuthors().add(eric);
		
		save(publisher, eric, dddBook);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "23456789");
		publisher.getBooks().add(noEJB);
		rod.getBooks().add(noEJB);
		
		noEJB.setPublisher(publisher);
		noEJB.getAuthors().add(rod);
		
		save(publisher, rod, noEJB);
		
		System.out.println("Publishers added: " + publisherRepository.count());
		System.out.println("Authors added: " + authorRepository.count());
		System.out.println("Books added: " + bookRepository.count());
	}
	
	private void save(Publisher publisher, Author author, Book book) {
		authorRepository.save(author);
		bookRepository.save(book);
		publisherRepository.save(publisher);		
	}
	
	private void save(Publisher publisher) {
		publisherRepository.save(publisher);
	}

}
