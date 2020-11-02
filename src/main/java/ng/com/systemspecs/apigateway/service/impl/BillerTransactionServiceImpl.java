package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.BillerTransactionService;
import ng.com.systemspecs.apigateway.domain.BillerTransaction;
import ng.com.systemspecs.apigateway.repository.BillerTransactionRepository;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillerTransaction}.
 */
@Service
@Transactional
public class BillerTransactionServiceImpl implements BillerTransactionService {

    private final Logger log = LoggerFactory.getLogger(BillerTransactionServiceImpl.class);

    private final BillerTransactionRepository billerTransactionRepository;

    private final BillerTransactionMapper billerTransactionMapper;

    public BillerTransactionServiceImpl(BillerTransactionRepository billerTransactionRepository, BillerTransactionMapper billerTransactionMapper) {
        this.billerTransactionRepository = billerTransactionRepository;
        this.billerTransactionMapper = billerTransactionMapper;
    }

    @Override
    public BillerTransactionDTO save(BillerTransactionDTO billerTransactionDTO) {
        log.debug("Request to save BillerTransaction : {}", billerTransactionDTO);
        BillerTransaction billerTransaction = billerTransactionMapper.toEntity(billerTransactionDTO);
        billerTransaction = billerTransactionRepository.save(billerTransaction);
        return billerTransactionMapper.toDto(billerTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillerTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillerTransactions");
        return billerTransactionRepository.findAll(pageable)
            .map(billerTransactionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillerTransactionDTO> findOne(Long id) {
        log.debug("Request to get BillerTransaction : {}", id);
        return billerTransactionRepository.findById(id)
            .map(billerTransactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillerTransaction : {}", id);
        billerTransactionRepository.deleteById(id);
    }
}
