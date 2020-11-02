package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.BillerCategoryService;
import ng.com.systemspecs.apigateway.domain.BillerCategory;
import ng.com.systemspecs.apigateway.repository.BillerCategoryRepository;
import ng.com.systemspecs.apigateway.service.dto.BillerCategoryDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillerCategory}.
 */
@Service
@Transactional
public class BillerCategoryServiceImpl implements BillerCategoryService {

    private final Logger log = LoggerFactory.getLogger(BillerCategoryServiceImpl.class);

    private final BillerCategoryRepository billerCategoryRepository;

    private final BillerCategoryMapper billerCategoryMapper;

    public BillerCategoryServiceImpl(BillerCategoryRepository billerCategoryRepository, BillerCategoryMapper billerCategoryMapper) {
        this.billerCategoryRepository = billerCategoryRepository;
        this.billerCategoryMapper = billerCategoryMapper;
    }

    @Override
    public BillerCategoryDTO save(BillerCategoryDTO billerCategoryDTO) {
        log.debug("Request to save BillerCategory : {}", billerCategoryDTO);
        BillerCategory billerCategory = billerCategoryMapper.toEntity(billerCategoryDTO);
        billerCategory = billerCategoryRepository.save(billerCategory);
        return billerCategoryMapper.toDto(billerCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillerCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillerCategories");
        return billerCategoryRepository.findAll(pageable)
            .map(billerCategoryMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillerCategoryDTO> findOne(Long id) {
        log.debug("Request to get BillerCategory : {}", id);
        return billerCategoryRepository.findById(id)
            .map(billerCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillerCategory : {}", id);
        billerCategoryRepository.deleteById(id);
    }
}
