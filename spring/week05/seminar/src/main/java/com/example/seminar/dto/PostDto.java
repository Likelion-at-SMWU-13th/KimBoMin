package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int boardId;

    @Override
    public String toString() {
        return "PostDto{"+"title="+title+'\''+
                ",content="+content+'\''+"}";
}

}