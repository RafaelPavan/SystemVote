package br.com.nt.voteSystem.repository.associate;

import br.com.nt.voteSystem.model.associate.AssociateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<AssociateModel, Long> {

    boolean existsByCpf(String cpf);
}
