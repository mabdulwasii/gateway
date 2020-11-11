package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.JournalLineService;
import ng.com.systemspecs.apigateway.service.ProfileService;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.ProfileRepository;
import ng.com.systemspecs.apigateway.service.dto.ProfileDTO;
import ng.com.systemspecs.apigateway.service.mapper.ProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link Profile}.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    private final JournalLineService journalLineService;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper,
                              JournalLineService journalLineService) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.journalLineService = journalLineService;
    }

    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        log.debug("Request to save Profile : {}", profileDTO);
        Profile profile = profileMapper.toEntity(profileDTO);
        profile = profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }
    @Override
    public Profile save(Profile profile) {
        log.debug("Request to save Profile : {}", profile);
        profile = profileRepository.save(profile);
        return profile;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profiles");
        return profileRepository.findAll(pageable)
            .map(profileMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDTO> findOne(Long id) {
        log.debug("Request to get Profile : {}", id);
        return profileRepository.findById(id)
            .map(profileMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        profileRepository.deleteById(id);
    }

    @Override
    public Optional<ProfileDTO> findOneByPhoneNumber(String phoneNumber) {
        Profile profile = profileRepository.findOneByPhoneNumber(phoneNumber);
        return Optional.of(profileMapper.toDto(profile));
    }

    @Override
    public Optional<ProfileDTO> findByUserIsCurrentUser() {
        Profile profile = profileRepository.findByUserIsCurrentUser();
        return Optional.of(profileMapper.toDto(profile));
    }

    @Override
    public Profile findByPhoneNumber(String phoneNumber) {
        // TODO Auto-generated method stub
        return profileRepository.findOneByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean canSpendOnAccount(String phoneNumber, Long accountNumber, Double amount) {
        Profile profile = profileRepository.findOneByPhoneNumber(phoneNumber);
        Double todaySpending = journalLineService.getAccountDailyTransactionAmount(accountNumber);
        return ((todaySpending+amount)<=profile.getKyc().getDailyTransactionLimit());
    }

    @Override
    public Boolean canAccummulateOnAccount(String phoneNumber, Long accountNumber, Double amount) {
        Profile profile = profileRepository.findOneByPhoneNumber(phoneNumber);
        Set<WalletAccount> accounts = profile.getWalletAccounts();
        Boolean response = false;
        for (WalletAccount walletAccount : accounts) {
            if(walletAccount.getAccountNumber() == accountNumber) {
                response = walletAccount.getCurrentBalance()+amount<=profile.getKyc().getCumulativeBalanceLimit();
            }
        }
        return response;
    }



}
