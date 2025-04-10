package example.day04._과제4.controller;

import example.day04._과제4.model.dto.ProductDto;
import example.day04._과제4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day04/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    // 등록
    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto){
        return productService.create(productDto);
    }
    
    // 전체 조회
    @GetMapping
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    // 개별 조회
    @GetMapping("/view")
    public ProductDto findById(@RequestParam int id){
        return productService.findById(id);
    }

    
    // 개별 수정
    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }
    
    // 개별 삭제
    @DeleteMapping
    public boolean delete(@RequestParam int id){
        return productService.delete(id);
    }

    // 페이징 처리 조회
    @GetMapping("/page")
    public List<ProductDto> findByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size){
        return productService.findByPage(page, size);
    }

}
