package zw.co.kez.paymentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.kez.paymentservice.models.TransactionDetails;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {
    Optional<TransactionDetails> findByCartId(Long cartId);
}
