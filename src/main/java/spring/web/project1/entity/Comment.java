package spring.web.project1.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comments_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String commentWriter;

    @ManyToOne
    @JoinColumn(name = "board_nno")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    public void update(String comment){
        this.comment = comment;
    }
}
