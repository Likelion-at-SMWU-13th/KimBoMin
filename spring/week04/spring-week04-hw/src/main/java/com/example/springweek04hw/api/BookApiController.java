package com.example.springweek04hw.api;

import com.example.springweek04hw.model.Book;
import com.example.springweek04hw.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Books", description = "Book CRUD API")
@RestController
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService svc;
    public BookApiController(BookService svc) {
        this.svc = svc;
        this.svc.initDummyIfEmpty();
    }

    @Operation(summary = "List books", description = "모든 책 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class)))
    })
    @GetMapping
    public List<Book> list() {
        return svc.findAll();
    }

    @Operation(summary = "Get a book", description = "ID로 책을 조회합니다.")
    @Parameters({ @Parameter(name = "id", description = "책 ID", required = true) })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "책을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return svc.findById(id);
    }

    @Operation(summary = "Create a book", description = "새 책을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공"),
            @ApiResponse(responseCode = "409", description = "중복 도서",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book create(@RequestBody Book req) {
        return svc.add(req);
    }

    @Operation(summary = "Delete a book", description = "ID로 책을 삭제합니다.")
    @Parameters({ @Parameter(name = "id", description = "책 ID", required = true) })
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "책을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
