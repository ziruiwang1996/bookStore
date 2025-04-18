package com.cse687.zirui.bookstore.payment.repository;
import com.cse687.zirui.bookstore.payment.domain.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
