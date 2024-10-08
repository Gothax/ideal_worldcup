package ideal.worldcup.repository;

import ideal.worldcup.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldcupRepository extends JpaRepository<Post, Long> {

}
