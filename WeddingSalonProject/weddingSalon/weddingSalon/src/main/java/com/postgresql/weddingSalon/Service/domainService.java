package com.postgresql.weddingSalon.Service;

import com.postgresql.weddingSalon.Entity.companyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.transaction.annotation.Transactional;


@Service
public class domainService {

    @Autowired
    private com.postgresql.weddingSalon.Repository.companyDataRepo companyDataRepo;
    @Transactional
    public void migrateDomain(String domainName, String newValue) {
        String constraintName = domainName + "_check";

        // Fetch existing values
        String constraintDef = companyDataRepo.getDomainCheckConstraint(constraintName).orElseThrow(() ->
                new NoSuchElementException("No domain check constraint " + constraintName + " exists"));;

        if (constraintDef == null) {
            throw new RuntimeException("Domain constraint not found: " + constraintName);
        }

        // Extract existing values
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(constraintDef);
        Set<String> values = new HashSet<>();

        while (matcher.find()) {
            values.add(matcher.group(1));
        }

        // Add new value if not already present
        if (values.contains(newValue)) {
            System.out.println("Value already exists in the domain.");
            return;
        }
        System.out.println(values);
        values.add(newValue);
        System.out.println(values);

        String formattedValues = values.stream().map(v -> "'" + v + "'").collect(Collectors.joining(", "));

        this.dropDomainConstraint(domainName, constraintName);
        this.addDomainConstraint(domainName, constraintName, formattedValues);

    }

    @Transactional
    public void deleteDomainValue(String domainName, String oldValue) {
        String constraintName = domainName + "_check";

        // Fetch existing values
        String constraintDef = companyDataRepo.getDomainCheckConstraint(constraintName).orElseThrow(() ->
                new NoSuchElementException("No domain check constraint " + constraintName + " exists"));;

        if (constraintDef == null) {
            throw new RuntimeException("Domain constraint not found: " + constraintName);
        }

        // Extract existing values
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(constraintDef);
        Set<String> values = new HashSet<>();

        while (matcher.find()) {
            values.add(matcher.group(1));
        }

        values.remove(oldValue);
        String formattedValues = values.stream().map(v -> "'" + v + "'").collect(Collectors.joining(", "));

        this.dropDomainConstraint(domainName, constraintName);
        this.addDomainConstraint(domainName, constraintName, formattedValues);

    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dropDomainConstraint(String domainName, String constraintName) {
        String sql = String.format("ALTER DOMAIN %s DROP CONSTRAINT %s", domainName, constraintName);
        jdbcTemplate.execute(sql);
    }

    public void addDomainConstraint(String domainName, String constraintName, String values) {
        String sql = String.format("ALTER DOMAIN %s ADD CONSTRAINT %s CHECK (VALUE IN (%s))",
                domainName, constraintName, values);
        jdbcTemplate.execute(sql);
    }





}
