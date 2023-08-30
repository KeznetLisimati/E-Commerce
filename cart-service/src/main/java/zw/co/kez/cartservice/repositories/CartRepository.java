package zw.co.kez.cartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.kez.cartservice.models.CartDetails;

public interface CartRepository extends JpaRepository <CartDetails, Long >{
}
