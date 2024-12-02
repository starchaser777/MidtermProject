package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.entity.Reply;
import kr.ac.kopo.midtermproject.entity.cBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 게시글 삭제시에 댓글 삭제
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno")
    void deleteByBno(Long bno);

    // 게시글 번호에 해당하는 댓글 목록 반환
    List<Reply> getRepliesByBoardOrderByRno(cBoard board);
}
