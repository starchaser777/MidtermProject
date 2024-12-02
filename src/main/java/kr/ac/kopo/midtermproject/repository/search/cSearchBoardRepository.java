package kr.ac.kopo.midtermproject.repository.search;

import kr.ac.kopo.midtermproject.entity.cBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface cSearchBoardRepository {

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
