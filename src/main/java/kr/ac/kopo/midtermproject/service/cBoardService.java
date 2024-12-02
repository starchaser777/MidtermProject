package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.BoardDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.dto.PageResultDTO;
import kr.ac.kopo.midtermproject.dto.cBoardDTO;
import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.entity.Member;
import kr.ac.kopo.midtermproject.entity.cBoard;

public interface cBoardService {
    // 새 글을 등록하는 기능
    Long register(cBoardDTO dto);

    // 게시목록 처리 기능
    PageResultDTO<cBoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 특정 게시글 하나를 조회하는 기능
    cBoardDTO get(Long bno);

    // 삭제 기능
    void removeWithReplies(Long bno);

    // 수정 기능
    void modify(cBoardDTO boardDTO);

    // Entity를 DTO로 변환하는 메소드
    default cBoardDTO entityToDTO(cBoard board, Member member, Long replyCount) {
        cBoardDTO boardDTO = cBoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }

    // DTO를 Entity로 변환하는 메소드
    default cBoard dtoToEntity(cBoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        cBoard board = cBoard.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }
}
