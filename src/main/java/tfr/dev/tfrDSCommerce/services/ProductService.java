package tfr.dev.tfrDSCommerce.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tfr.dev.tfrDSCommerce.dto.ProductDTO;
import tfr.dev.tfrDSCommerce.entities.Product;
import tfr.dev.tfrDSCommerce.repositories.ProductRepository;
import org.springframework.data.domain.Sort.Direction;
import tfr.dev.tfrDSCommerce.services.exceptions.DataBaseException;
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


    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repo.getReferenceById(id);
            copyToDto(dto, entity);
            entity = repo.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

      private void copyToDto(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
      }
    }


