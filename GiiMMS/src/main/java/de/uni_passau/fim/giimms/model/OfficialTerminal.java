package de.uni_passau.fim.giimms.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "officialTerminal")
public class OfficialTerminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;
    @Embedded
    @Column(name = "coordinates")
    protected Coordinates coordinates;
    @Column(name = "branchName")
    protected String branchName;

    public OfficialTerminal(Coordinates coordinates, String branchName){
        this.coordinates = coordinates;
        this.branchName = branchName;
    }
}