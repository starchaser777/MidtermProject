package kr.ac.kopo.midtermproject.service;

import jakarta.transaction.Transactional;
import kr.ac.kopo.midtermproject.dto.BoardDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.dto.PageResultDTO;
import kr.ac.kopo.midtermproject.entity.Board;
import kr.ac.kopo.midtermproject.entity.Member;
import kr.ac.kopo.midtermproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;

    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);
        repository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Member) en[1]));

//        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        Page<Object[]> result = repository.searchPage(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;
        BoardDTO boardDTO = entityToDTO((Board) arr[0], (Member) arr[1]);

        return boardDTO;
    }

    @Transactional // 하나의 메소드에서 여러 개의 작업을 한 작업으로 처리할 때 사용(두 개의 기능, 과정 -> 하나의 작업일 때)
    @Override
    public void removeWithReplies(Long bno) {
        // 원글 삭제
        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = repository.getReferenceById(boardDTO.getBno()); // getOne, getReferenceById - 지연로딩에서 사용하는 메소드
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        repository.save(board);
    }
}
