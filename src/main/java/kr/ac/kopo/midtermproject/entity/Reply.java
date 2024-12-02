package kr.ac.kopo.midtermproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "cBoard") // 외부에 있는 것을 참조함
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replyer;

    // 지연로딩 방식으로 수정 이유: 즉시로딩을 사용할 경우 불필요한 Join을 하므로 성능을 저하시킬 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private cBoard board; // Foreign Key 설정(참조무결성 유지), 실제이름 board_bno
}
