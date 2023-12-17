package tfr.dev.tfrDSCommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfr.dev.tfrDSCommerce.dto.ProductDTO;
import tfr.dev.tfrDSCommerce.entities.Product;
import tfr.dev.tfrDSCommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Product> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
