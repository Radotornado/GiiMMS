package de.uni_passau.fim.giimms.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "otuPasswords")
public class OTUPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;

    @Column(name = "username", unique = true, nullable = false)
    protected String username;

    @Column(name ="date", unique = true, nullable = false)
    protected Date expires;

    public OTUPassword(String username){
        this.username = username;
        long DAY_IN_MS = 1000*60*60*24;
        Date date = new Date();
        this.expires = new Date(date.getTime() + (7* DAY_IN_MS));
    }
}
