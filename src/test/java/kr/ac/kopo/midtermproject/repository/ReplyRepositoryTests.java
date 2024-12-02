package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.entity.Reply;
import kr.ac.kopo.midtermproject.entity.cBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testListByBoard() {
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(cBoard.builder().bno(100L).build());
        replyList.forEach(reply -> System.out.println(reply));
    }

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long)(Math.random() * 100 + 1); //1~100 임의의 long형의 정수 값

            cBoard board = cBoard.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                    .text("Reply "+i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }
}