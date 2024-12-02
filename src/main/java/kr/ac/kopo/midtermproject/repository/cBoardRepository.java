package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.entity.cBoard;
import kr.ac.kopo.midtermproject.repository.search.SearchBoardRepository;
import kr.ac.kopo.midtermproject.repository.search.cSearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface cBoardRepository extends JpaRepository<cBoard, Long>, cSearchBoardRepository {
    @Query("select b, w from cBoard b left join b.writer w where b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from cBoard b left join Reply r ON r.board=b where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count(r) from cBoard b "
            + "left join b.writer w "
            + "left join Reply r on r.board = b "
            + "group by b, w", // w 추가
            countQuery = "select count(b) from cBoard b"
    )
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w, count(r) "
            + "from cBoard b left join b.writer w "
            + "left outer join Reply r on r.board = b "
            + "where b.bno = :bno "
            + "group by b, w") // group by b, w 추가
    Object getBoardByBno(@Param("bno")Long bno);

}
