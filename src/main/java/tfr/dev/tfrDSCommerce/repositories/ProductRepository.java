package tfr.dev.tfrDSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfr.dev.tfrDSCommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
