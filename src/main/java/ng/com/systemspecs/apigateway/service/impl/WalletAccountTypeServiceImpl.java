package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.WalletAccountTypeService;
import ng.com.systemspecs.apigateway.domain.WalletAccountType;
import ng.com.systemspecs.apigateway.repository.WalletAccountTypeRepository;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountTypeDTO;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WalletAccountType}.
 */
@Service
@Transactional
public class WalletAccountTypeServiceImpl implements WalletAccountTypeService {

    private final Logger log = LoggerFactory.getLogger(WalletAccountTypeServiceImpl.class);

    private final WalletAccountTypeRepository walletAccountTypeRepository;

    private final WalletAccountTypeMapper walletAccountTypeMapper;

    public WalletAccountTypeServiceImpl(WalletAccountTypeRepository walletAccountTypeRepository, WalletAccountTypeMapper walletAccountTypeMapper) {
        this.walletAccountTypeRepository = walletAccountTypeRepository;
        this.walletAccountTypeMapper = walletAccountTypeMapper;
    }

    @Override
    public WalletAccountTypeDTO save(WalletAccountTypeDTO walletAccountTypeDTO) {
        log.debug("Request to save WalletAccountType : {}", walletAccountTypeDTO);
        WalletAccountType walletAccountType = walletAccountTypeMapper.toEntity(walletAccountTypeDTO);
        walletAccountType = walletAccountTypeRepository.save(walletAccountType);
        return walletAccountTypeMapper.toDto(walletAccountType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WalletAccountTypeDTO> findAll() {
        log.debug("Request to get all WalletAccountTypes");
        return walletAccountTypeRepository.findAll().stream()
            .map(walletAccountTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WalletAccountTypeDTO> findOne(Long id) {
        log.debug("Request to get WalletAccountType : {}", id);
        return walletAccountTypeRepository.findById(id)
            .map(walletAccountTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WalletAccountType : {}", id);
        walletAccountTypeRepository.deleteById(id);
    }
}
