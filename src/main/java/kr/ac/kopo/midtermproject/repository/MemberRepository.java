package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
