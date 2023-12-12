package tfr.dev.tfrDSCommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfr.dev.tfrDSCommerce.dto.ProductDTO;
import tfr.dev.tfrDSCommerce.entities.Product;
import tfr.dev.tfrDSCommerce.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = repo.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;
    }

}
