package com.postgresql.weddingSalon.Controller;

import com.postgresql.weddingSalon.DTO.companyDataDTO;
import com.postgresql.weddingSalon.Entity.companyData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/company")
public class companyDataController {

    private final com.postgresql.weddingSalon.Repository.companyDataRepo companyDataRepo;
    private final com.postgresql.weddingSalon.Service.companyDataService companyDataService;
    private final com.postgresql.weddingSalon.Service.domainService domainService;

    public companyDataController(com.postgresql.weddingSalon.Repository.companyDataRepo companyDataRepo,
                                 com.postgresql.weddingSalon.Service.companyDataService companyDataService,
                                 com.postgresql.weddingSalon.Service.domainService domainService
                                 ) {
        this.companyDataRepo = companyDataRepo;
        this.companyDataService = companyDataService;
        this.domainService = domainService;
    }

    @GetMapping
    public ResponseEntity getAllCompanies(){
        List<companyDataDTO> companies = companyDataService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") Long id) {
        companyData existingCompany = companyDataRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("no company with ID " + id + " exists")
        );
        return ResponseEntity.ok(this.companyDataRepo.findById(id).orElse(null));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<companyData[]> getCompanyByName(@PathVariable("name") String name) {
        companyData[] companies = companyDataRepo.findByCompanyContaining(name).orElseThrow(() ->
                new NoSuchElementException("No company with name " + name + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/by-rep/{rep}")
    public ResponseEntity<companyData[]> getCompanyByRep(@PathVariable("rep") String rep) {
        companyData[] companies = companyDataRepo.findByRep(rep).orElseThrow(() ->
                new NoSuchElementException("No company with rep " + rep + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<companyData[]> getCompanyByEmail(@PathVariable("email") String email) {
        companyData[] companies = companyDataRepo.findByEmailContaining(email).orElseThrow(() ->
                new NoSuchElementException("No company with email " + email + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/by-firstName/{firstName}")
    public ResponseEntity<companyData[]> getCompanyByFirstName(@PathVariable("firstName") String firstName) {
        companyData[] companies = companyDataRepo.findByFirstNameContaining(firstName).orElseThrow(() ->
                new NoSuchElementException("No company with first name " + firstName + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/by-lastName/{lastName}")
    public ResponseEntity<companyData[]> getCompanyByLast_name(@PathVariable("lastName") String lastName) {
        companyData[] companies = companyDataRepo.findByLastNameContaining(lastName).orElseThrow(() ->
                new NoSuchElementException("No company with last name " + lastName + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/by-clientType/{clientType}")
    public ResponseEntity<companyData[]> getCompanyByClientType(@PathVariable("clientType") String clientType) {
        companyData[] companies = companyDataRepo.findByTargetClientContaining(clientType).orElseThrow(() ->
                new NoSuchElementException("No company that client type " + clientType + " exists"));

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/add-domain/{domainName}/{value}")
    public ResponseEntity<String> getAddDomainValue(@PathVariable String domainName, @PathVariable String value) {
        domainService.migrateDomain(domainName, value);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/delete-domain/{domainName}/{value}")
    public ResponseEntity<String> getDeleteDomainValue(@PathVariable String domainName, @PathVariable String value) {
        domainService.deleteDomainValue(domainName, value);
        return ResponseEntity.ok("Success");
    }


    @GetMapping("/get-domain/{domainName}")
    public ResponseEntity<List<String>> getRepDomain(@PathVariable String domainName) {
        String domain = companyDataRepo.getDomainCheckConstraint(domainName+"_check").orElseThrow(() ->
                new NoSuchElementException("No domain was able to be retrieved"));
        List<String> values = new ArrayList<>();

        // Regular expression to match values inside single quotes
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(domain);

        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return ResponseEntity.ok(values);
    }




//    @GetMapping("/by-location")
//    public ResponseEntity<companyData[]> getCompanyByLocation(
//            @RequestParam String ny,
//            @RequestParam String il,
//            @RequestParam String la,
//            @RequestParam String fl,
//            @RequestParam String tx,
//            @RequestParam String dc) {
//
//        companyData[] companies = companyDataRepo.findByStates(ny, il, la, fl, tx, dc).orElseThrow(() ->
//                new NoSuchElementException("No company with those locations exist"));
//
//        return ResponseEntity.ok(companies);
//    }


    @PostMapping
    public ResponseEntity saveCompany(@RequestBody companyDataDTO companyDataDTO){
        System.out.println("Got to post");
        companyData companyData = companyDataService.toEntity(companyDataDTO);
        return new ResponseEntity(this.companyDataRepo.save(companyData), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateCompanyById(@RequestBody companyDataDTO companyData) {
        System.out.println("Update company by id called");
        Long id = companyData.getId();
        companyData.setId(null);
        companyData existingCompany = companyDataRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("no company with ID " + id + " exists")
        );
        for (Field field : companyData.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(companyData);
                if (value != null) {
                    Field entityField = existingCompany.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(existingCompany, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity(this.companyDataRepo.save(existingCompany), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable("id") Long id) {
        companyData existingCompany = companyDataRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("no company with ID " + id + " exists")
        );
        this.companyDataRepo.deleteById(id);
        return new ResponseEntity("Company Deleted Successfully", HttpStatus.OK);
    }
}
