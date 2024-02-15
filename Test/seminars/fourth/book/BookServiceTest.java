package seminars.fourth.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {

    InMemoryBookRepository inMemoryBookRepository;
    BookService bookService;
    Book book1, book2;
    @BeforeEach
    void setUp() {
        inMemoryBookRepository = mock(InMemoryBookRepository.class);
        bookService = new BookService(inMemoryBookRepository);
        book1 = new Book("12", "Сердце Пармы", "Алексей Иванов");
        book2 = new Book("439", "Псоглавцы", "Алексей Маврин");
    }

    @Test
    void bookServiceFindByIdTest() {
        when(inMemoryBookRepository.findById(anyString())).thenReturn(book1);

        Book book = bookService.findBookById("104");

        assertThat(book)
                .isNotNull()
                .isInstanceOf(Book.class)
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("title")
                .hasFieldOrProperty("author");
    }

        @Test
        void bookServiceFindAllTest() {
            when(inMemoryBookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

            List<Book> books = bookService.findAllBooks();

            assertThat(books)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(2)
                    .contains(book1, book2);
        }

}