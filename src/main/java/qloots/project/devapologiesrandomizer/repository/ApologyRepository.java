package qloots.project.devapologiesrandomizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qloots.project.devapologiesrandomizer.entity.Apology;

@Repository
public interface ApologyRepository extends JpaRepository<Apology, Long> {

    Apology findByHttpCode(int httpCode);
}
