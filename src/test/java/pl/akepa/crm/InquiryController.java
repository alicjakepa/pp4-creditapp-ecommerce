package pl.akepa.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InquiryController {
    @Autowired
    InquiryCRUD inquiryCRUD;
    public List<Inquiry> all() {
        return  inquiryCRUD.findAll();
    }

    public void createInquiry(@RequestBody Inquiry inquiry) {
        inquiryCRUD.save(inquiry);
    }
}
