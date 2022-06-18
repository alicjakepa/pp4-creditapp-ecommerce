package pl.akepa.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InquiryController {
    @Autowired
    InquiryCRUD inquiryCRUD;
    @GetMapping("/api/inquiry")
    public List<Inquiry> all() {
        return  inquiryCRUD.findAll();
    }

    @PostMapping("/api/inquiry")
    public void createInquiry(@RequestBody Inquiry inquiry) {
        inquiryCRUD.save(inquiry);
    }
}
