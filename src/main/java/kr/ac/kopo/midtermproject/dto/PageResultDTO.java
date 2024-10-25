package kr.ac.kopo.midtermproject.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    // DTO 리스트
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    // 이전, 다음
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages(); // result는 Page<Guestbook>
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        // temp end page. 올림하는 메서드
        // 현재 페이지를 기준으로 화면에 출력되어야 하는 마지막 페이지 번호를 우선 처리
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        // 화면의 시작페이지 번호
        start = tempEnd - 9;

        // 기타 필요한 이전 페이지 여부 계산
        prev = start > 1; // 2~마지막 화면까지 true

        // 실제 데이터가 부족한 경우를 위해 마지막 페이지 번호는 전체 데이터의 개수를 이용해서 다시 계산
        // 삼항조건연산자에서 조건식 true면 마지막 화면이 아닌 경우 / false면 마지막 화면이라는 의미
        // 전체 페이지 번호가 31일 때: 마지막 화면이 아닌 경우 1~3번째 화면(10, 20, 30), 마지막 화면은 4번째 화면을 의미(31)
        end = totalPage > tempEnd ? tempEnd: totalPage;

        // 기타 필요한 다음 페이지 존재 여부 계산
        next = totalPage > tempEnd; // 1~마지막 바로 전화면까지 true

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
