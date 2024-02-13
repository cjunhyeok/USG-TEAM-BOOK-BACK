package com.usg.book.adapter.out.api;

import com.usg.book.adapter.out.api.dto.DocsDto;
import com.usg.book.adapter.out.api.dto.LibraryIsbnResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookISBNCheckAdapterTest {

    @InjectMocks
    private BookISBNCheckAdapter bookISBNCheckAdapter;
    @Mock
    private RestTemplate restTemplate;

    @Test
    @DisplayName("국립 도서 api 목킹, isbn 확인 로직 테스트")
    void bookIsbnCheckTest() {
        // given
        String isbn = "isbn";
        Integer price = 30000;
        List<DocsDto> docs = new ArrayList<>();
        DocsDto docsDto = new DocsDto();
        docsDto.setPRE_PRICE(price);
        docsDto.setEA_ISBN(isbn);
        docs.add(docsDto);
        LibraryIsbnResponse libraryIsbnResponse = new LibraryIsbnResponse();
        libraryIsbnResponse.setDocs(docs);

        // stub
        when(restTemplate.getForObject(any(URI.class), eq(LibraryIsbnResponse.class))).thenReturn(libraryIsbnResponse);

        // when
        bookISBNCheckAdapter.bookIsbnCheck(isbn, price);
    }
}
