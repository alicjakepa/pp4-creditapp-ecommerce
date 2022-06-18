package pl.akepa.crm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InquiryCRUD
        extends JpaRepository<Inquiry, Long> {
}