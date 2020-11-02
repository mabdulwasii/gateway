package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.BillerService;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.repository.BillerRepository;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Biller}.
 */
@Service
@Transactional
public class BillerServiceImpl implements BillerService {

    private final Logger log = LoggerFactory.getLogger(BillerServiceImpl.class);

    private final BillerRepository billerRepository;

    private final BillerMapper billerMapper;

    public BillerServiceImpl(BillerRepository billerRepository, BillerMapper billerMapper) {
        this.billerRepository = billerRepository;
        this.billerMapper = billerMapper;
    }

    @Override
    public BillerDTO save(BillerDTO billerDTO) {
        log.debug("Request to save Biller : {}", billerDTO);
        Biller biller = billerMapper.toEntity(billerDTO);
        biller = billerRepository.save(biller);
        return billerMapper.toDto(biller);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Billers");
        return billerRepository.findAll(pageable)
            .map(billerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillerDTO> findOne(Long id) {
        log.debug("Request to get Biller : {}", id);
        return billerRepository.findById(id)
            .map(billerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Biller : {}", id);
        billerRepository.deleteById(id);
    }
}
