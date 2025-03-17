package com.postgresql.weddingSalon.Repository;

import com.postgresql.weddingSalon.Entity.companyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface companyDataRepo extends JpaRepository<companyData, Long> {
    // Custom query methods can be added here
    public List<companyData> findByOrderByIdAsc();
    public Optional<companyData[]> findByCompanyContaining(String name);
    public Optional<companyData[]> findByRep(String rep);
    public Optional<companyData[]> findByEmailContaining(String email);
    public Optional<companyData[]> findByFirstNameContaining(String firstName);
    public Optional<companyData[]> findByLastNameContaining(String lastName);
    public Optional<companyData[]> findByTargetClientContaining(String clientType);
    @Query(value = "SELECT pg_get_constraintdef(oid) FROM pg_constraint WHERE conrelid = 0 AND conname = 'rep_domain_check'", nativeQuery = true)
    public Optional<String> getRepDomain();


    @Query(value = "SELECT pg_get_constraintdef(oid) FROM pg_constraint WHERE conrelid = 0 AND conname = :constraintName", nativeQuery = true)
    public Optional<String> getDomainCheckConstraint(@Param("constraintName") String constraintName);

//    @Query("SELECT c FROM CompanyData c WHERE " +
//            "(:ny IS NULL OR c.ny IS NOT NULL) AND " +
//            "(:il IS NULL OR c.il IS NOT NULL) AND " +
//            "(:la IS NULL OR c.la IS NOT NULL) AND " +
//            "(:fl IS NULL OR c.fl IS NOT NULL) AND " +
//            "(:tx IS NULL OR c.tx IS NOT NULL) AND " +
//            "(:dc IS NULL OR c.dc IS NOT NULL)")
//    Optional<companyData[]> findByStates(
//            @Param("ny") String ny,
//            @Param("il") String il,
//            @Param("la") String la,
//            @Param("fl") String fl,
//            @Param("tx") String tx,
//            @Param("dc") String dc
//    );
}
