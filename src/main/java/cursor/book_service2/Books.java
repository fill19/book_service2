package cursor.book_service2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Books {
    private long id;
    private String name;
    private boolean readBefore;

    public void Books(String name) {
        this.name = name;
    }
}