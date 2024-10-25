package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b from Board b where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w from Board b "
            + "left join b.writer w "
            + "group by b, w", // w 추가
            countQuery = "select count(b) from Board b"
    )
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w "
            + "from Board b left join b.writer w "
            + "where b.bno = :bno "
            + "group by b, w") // group by b, w 추가
    Object getBoardByBno(@Param("bno")Long bno);
}
