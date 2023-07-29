package med.voll.api.domain.cancelamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {
}
