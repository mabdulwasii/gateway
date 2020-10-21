package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.SchemeCategoryService;
import ng.com.systemspecs.apigateway.domain.SchemeCategory;
import ng.com.systemspecs.apigateway.repository.SchemeCategoryRepository;
import ng.com.systemspecs.apigateway.service.dto.SchemeCategoryDTO;
import ng.com.systemspecs.apigateway.service.mapper.SchemeCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SchemeCategory}.
 */
@Service
@Transactional
public class SchemeCategoryServiceImpl implements SchemeCategoryService {

    private final Logger log = LoggerFactory.getLogger(SchemeCategoryServiceImpl.class);

    private final SchemeCategoryRepository schemeCategoryRepository;

    private final SchemeCategoryMapper schemeCategoryMapper;

    public SchemeCategoryServiceImpl(SchemeCategoryRepository schemeCategoryRepository, SchemeCategoryMapper schemeCategoryMapper) {
        this.schemeCategoryRepository = schemeCategoryRepository;
        this.schemeCategoryMapper = schemeCategoryMapper;
    }

    @Override
    public SchemeCategoryDTO save(SchemeCategoryDTO schemeCategoryDTO) {
        log.debug("Request to save SchemeCategory : {}", schemeCategoryDTO);
        SchemeCategory schemeCategory = schemeCategoryMapper.toEntity(schemeCategoryDTO);
        schemeCategory = schemeCategoryRepository.save(schemeCategory);
        return schemeCategoryMapper.toDto(schemeCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchemeCategoryDTO> findAll() {
        log.debug("Request to get all SchemeCategories");
        return schemeCategoryRepository.findAll().stream()
            .map(schemeCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SchemeCategoryDTO> findOne(Long id) {
        log.debug("Request to get SchemeCategory : {}", id);
        return schemeCategoryRepository.findById(id)
            .map(schemeCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SchemeCategory : {}", id);
        schemeCategoryRepository.deleteById(id);
    }
}
