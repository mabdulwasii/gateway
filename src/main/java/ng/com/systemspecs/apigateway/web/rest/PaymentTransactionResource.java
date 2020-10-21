package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.PaymentTransactionService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.PaymentTransaction}.
 */
@RestController
@RequestMapping("/api")
public class PaymentTransactionResource {

    private final Logger log = LoggerFactory.getLogger(PaymentTransactionResource.class);

    private static final String ENTITY_NAME = "paymentTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentTransactionService paymentTransactionService;

    public PaymentTransactionResource(PaymentTransactionService paymentTransactionService) {
        this.paymentTransactionService = paymentTransactionService;
    }

    /**
     * {@code POST  /payment-transactions} : Create a new paymentTransaction.
     *
     * @param paymentTransactionDTO the paymentTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentTransactionDTO, or with status {@code 400 (Bad Request)} if the paymentTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-transactions")
    public ResponseEntity<PaymentTransactionDTO> createPaymentTransaction(@Valid @RequestBody PaymentTransactionDTO paymentTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentTransaction : {}", paymentTransactionDTO);
        if (paymentTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentTransactionDTO result = paymentTransactionService.save(paymentTransactionDTO);
        return ResponseEntity.created(new URI("/api/payment-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-transactions} : Updates an existing paymentTransaction.
     *
     * @param paymentTransactionDTO the paymentTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the paymentTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-transactions")
    public ResponseEntity<PaymentTransactionDTO> updatePaymentTransaction(@Valid @RequestBody PaymentTransactionDTO paymentTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentTransaction : {}", paymentTransactionDTO);
        if (paymentTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentTransactionDTO result = paymentTransactionService.save(paymentTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-transactions} : get all the paymentTransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentTransactions in body.
     */
    @GetMapping("/payment-transactions")
    public ResponseEntity<List<PaymentTransactionDTO>> getAllPaymentTransactions(Pageable pageable) {
        log.debug("REST request to get a page of PaymentTransactions");
        Page<PaymentTransactionDTO> page = paymentTransactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-transactions/:id} : get the "id" paymentTransaction.
     *
     * @param id the id of the paymentTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-transactions/{id}")
    public ResponseEntity<PaymentTransactionDTO> getPaymentTransaction(@PathVariable Long id) {
        log.debug("REST request to get PaymentTransaction : {}", id);
        Optional<PaymentTransactionDTO> paymentTransactionDTO = paymentTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentTransactionDTO);
    }

    /**
     * {@code DELETE  /payment-transactions/:id} : delete the "id" paymentTransaction.
     *
     * @param id the id of the paymentTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-transactions/{id}")
    public ResponseEntity<Void> deletePaymentTransaction(@PathVariable Long id) {
        log.debug("REST request to delete PaymentTransaction : {}", id);
        paymentTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
