package qloots.project.devapologiesrandomizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "apology")
@Getter
@Setter
public class Apology {

    @Id
    @Column(name = "http_code")
    private int httpCode;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "message", nullable = false)
    private String message;
}
