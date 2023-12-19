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
import tfr.dev.tfrDSCommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
          Product entity = repo.findById(id).orElseThrow(
                   ()-> new ResourceNotFoundException("invalid id"));
           return new ProductDTO(entity);

        }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repo.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
         return repo.findAll(pageRequest);
        }

        @Transactional
        public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyToDto(dto, entity);
        entity = repo.save(entity);
        return new ProductDTO(entity);
        }


        @Transactional
        public ProductDTO update(Long id, ProductDTO dto){
        Product entity = repo.getReferenceById(id); // não vai ao BD. É monitorado pela JPA.
        copyToDto(dto, entity);
        entity = repo.save(entity);
        return new ProductDTO(entity);
        }

        @Transactional
        public void delete(Long id){
        repo.deleteById(id);
        }

      private void copyToDto(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
      }
    }


