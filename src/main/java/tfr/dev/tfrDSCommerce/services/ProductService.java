package tfr.dev.tfrDSCommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfr.dev.tfrDSCommerce.dto.ProductDTO;
import tfr.dev.tfrDSCommerce.entities.Product;
import tfr.dev.tfrDSCommerce.repositories.ProductRepository;
import org.springframework.data.domain.Sort.Direction;

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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repo.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
         return repo.findAll(pageRequest);
        }
    }


