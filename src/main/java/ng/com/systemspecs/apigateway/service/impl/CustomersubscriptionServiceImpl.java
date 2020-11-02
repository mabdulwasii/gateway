package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.CustomersubscriptionService;
import ng.com.systemspecs.apigateway.domain.Customersubscription;
import ng.com.systemspecs.apigateway.repository.CustomersubscriptionRepository;
import ng.com.systemspecs.apigateway.service.dto.CustomersubscriptionDTO;
import ng.com.systemspecs.apigateway.service.mapper.CustomersubscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Customersubscription}.
 */
@Service
@Transactional
public class CustomersubscriptionServiceImpl implements CustomersubscriptionService {

    private final Logger log = LoggerFactory.getLogger(CustomersubscriptionServiceImpl.class);

    private final CustomersubscriptionRepository customersubscriptionRepository;

    private final CustomersubscriptionMapper customersubscriptionMapper;

    public CustomersubscriptionServiceImpl(CustomersubscriptionRepository customersubscriptionRepository, CustomersubscriptionMapper customersubscriptionMapper) {
        this.customersubscriptionRepository = customersubscriptionRepository;
        this.customersubscriptionMapper = customersubscriptionMapper;
    }

    @Override
    public CustomersubscriptionDTO save(CustomersubscriptionDTO customersubscriptionDTO) {
        log.debug("Request to save Customersubscription : {}", customersubscriptionDTO);
        Customersubscription customersubscription = customersubscriptionMapper.toEntity(customersubscriptionDTO);
        customersubscription = customersubscriptionRepository.save(customersubscription);
        return customersubscriptionMapper.toDto(customersubscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomersubscriptionDTO> findAll() {
        log.debug("Request to get all Customersubscriptions");
        return customersubscriptionRepository.findAll().stream()
            .map(customersubscriptionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CustomersubscriptionDTO> findOne(Long id) {
        log.debug("Request to get Customersubscription : {}", id);
        return customersubscriptionRepository.findById(id)
            .map(customersubscriptionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customersubscription : {}", id);
        customersubscriptionRepository.deleteById(id);
    }
}
