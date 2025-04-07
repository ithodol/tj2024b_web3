package example.day04._과제4.service;

import example.day04._과제4.model.dto.ProductDto;
import example.day04._과제4.model.entity.ProductEntity;
import example.day04._과제4.model.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    // 등록
    public ProductDto create(ProductDto productDto){
        ProductEntity productEntity = productDto.toEntity();
        ProductEntity saveEntity = productRepository.save(productEntity);
        if(saveEntity.getId() >= 1){
            return saveEntity.toDto();
        }else{
            return null;
        }
    }


    // 전체 조회
    public List<ProductDto> findAll(){
        return productRepository.findAll().stream()
                .map(ProductEntity :: toDto)
                .collect(Collectors.toList());
    }

    // 개별 조회
    public ProductDto findById(int id){
        return productRepository.findById(id)
                .map(ProductEntity :: toDto)
                .orElse(null);
    }

    // 개별 수정
    public ProductDto update(ProductDto productDto){
        return productRepository.findById(productDto.getId())
                .map(entity -> {
                            entity.setName(productDto.getName());
                            entity.setDescription(productDto.getDescription());
                            entity.setQuantity(productDto.getQuantity());
                            return entity.toDto();
                })
                .orElse(null);
    }

    // 개별 삭제
    public boolean delete(int id){
        return productRepository.findById(id)
                .map((entity) -> {
                            productRepository.deleteById(id);
                            return true;
                })
                .orElse(false);
    }

    // 페이징 처리 조회
    public List<ProductDto> findByPage(int page, int size){
        PageRequest pageRequest = PageRequest.of(page -1, size, Sort.by(Sort.Direction.DESC, "id"));
        return productRepository.findAll(pageRequest).stream()
                .map(ProductEntity :: toDto)
                .collect(Collectors.toList());
    }
}
