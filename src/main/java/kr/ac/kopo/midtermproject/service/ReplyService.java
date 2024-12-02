package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.ReplyDTO;
import kr.ac.kopo.midtermproject.entity.Reply;
import kr.ac.kopo.midtermproject.entity.cBoard;

import java.util.List;

public interface ReplyService {
    // 댓글 등록 기능
    Long register(ReplyDTO replyDTO);
    // 댓글 수정 기능
    void modify(ReplyDTO replyDTO);
    // 댓글 삭제 기능
    void remove(Long rno);
    // 댓글 목록 반환 기능
    List<ReplyDTO> getList(Long bno);

    // ReplyDTO => Reply(Entity) 변환
    default Reply dtoToEntity(ReplyDTO replyDTO) {
        cBoard board = cBoard.builder()
                .bno(replyDTO.getBno())
                .build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    // Reply(Entity) => ReplyDTO 변환
    default ReplyDTO entityToDTO(Reply reply) {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .bno(reply.getBoard().getBno())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return replyDTO;
    }
}
