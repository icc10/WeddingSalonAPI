package com.postgresql.weddingSalon.Service;

import com.postgresql.weddingSalon.DTO.companyDataDTO;
import com.postgresql.weddingSalon.Entity.companyData;
import com.postgresql.weddingSalon.Repository.companyDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class companyDataService {

    @Autowired private companyDataRepo companyDataRepo;

    public companyData toEntity(companyDataDTO companyDataDTO){
        companyData companyData = new companyData();
        companyData.setLeadDate(companyDataDTO.getLeadDate());
        companyData.setLastCall(companyDataDTO.getLastCall());
        companyData.setLastFollowUp(companyDataDTO.getLastFollowUp());
        companyData.setRep(companyDataDTO.getRep());
        companyData.setOrigin(companyDataDTO.getOrigin());
        companyData.setCompany(companyDataDTO.getCompany());
        companyData.setCategory(companyDataDTO.getCategory());
        companyData.setFirstName(companyDataDTO.getFirstName());
        companyData.setLastName(companyDataDTO.getLastName());
        companyData.setTitle(companyDataDTO.getTitle());
        companyData.setPriority(companyDataDTO.getPriority());
        companyData.setEmail(companyDataDTO.getEmail());
        companyData.setLastEmailClient(companyDataDTO.getLastEmailClient());
        companyData.setNote(companyDataDTO.getNote());
        companyData.setTempType(companyDataDTO.getTempType());
        companyData.setStatus(companyDataDTO.getStatus());
        companyData.setNy(companyDataDTO.getNy());
        companyData.setLa(companyDataDTO.getLa());
        companyData.setFl(companyDataDTO.getFl());
        companyData.setTx(companyDataDTO.getTx());
        companyData.setIl(companyDataDTO.getIl());
        companyData.setDc(companyDataDTO.getDc());
        companyData.setLastAction(companyDataDTO.getLastAction());
        companyData.setShowOutreach(companyDataDTO.getShowOutreach());
        companyData.setSaidNoTo(companyDataDTO.getSaidNoTo());
        companyData.setTargetClient(companyDataDTO.getTargetClient());
        companyData.setEmailAction(companyDataDTO.getEmailAction());
        companyData.setMobile(companyDataDTO.getMobile());
        companyData.setOffice(companyDataDTO.getOffice());
        companyData.setAddress(companyDataDTO.getAddress());
        companyData.setCity(companyDataDTO.getCity());
        companyData.setZip(companyDataDTO.getZip());
        companyData.setSocialMedia(companyDataDTO.getSocialMedia());
        companyData.setNextFollowUp(companyDataDTO.getNextFollowUp());
        companyData.setInquiryStatus(companyDataDTO.getInquiryStatus());
        companyData.setWebsite(companyDataDTO.getWebsite());
        companyData.setVendorTour(companyDataDTO.getVendorTour());
        companyData.setCalendlySource(companyDataDTO.getCalendlySource());
        companyData.setRecommender(companyDataDTO.getRecommender());
        companyData.setInquiryOrigin(companyDataDTO.getInquiryOrigin());

        return companyData;
    }


    public companyDataDTO toDTO(companyData companyData){
        companyDataDTO companyDataDTO = new companyDataDTO();
        companyDataDTO.setLeadDate(companyData.getLeadDate());
        companyDataDTO.setLastCall(companyData.getLastCall());
        companyDataDTO.setLastFollowUp(companyData.getLastFollowUp());
        companyDataDTO.setRep(companyData.getRep());
        companyDataDTO.setOrigin(companyData.getOrigin());
        companyDataDTO.setCompany(companyData.getCompany());
        companyDataDTO.setCategory(companyData.getCategory());
        companyDataDTO.setFirstName(companyData.getFirstName());
        companyDataDTO.setLastName(companyData.getLastName());
        companyDataDTO.setTitle(companyData.getTitle());
        companyDataDTO.setPriority(companyData.getPriority());
        companyDataDTO.setEmail(companyData.getEmail());
        companyDataDTO.setLastEmailClient(companyData.getLastEmailClient());
        companyDataDTO.setNote(companyData.getNote());
        companyDataDTO.setTempType(companyData.getTempType());
        companyDataDTO.setStatus(companyData.getStatus());
        companyDataDTO.setNy(companyData.getNy());
        companyDataDTO.setLa(companyData.getLa());
        companyDataDTO.setFl(companyData.getFl());
        companyDataDTO.setTx(companyData.getTx());
        companyDataDTO.setIl(companyData.getIl());
        companyDataDTO.setDc(companyData.getDc());
        companyDataDTO.setLastAction(companyData.getLastAction());
        companyDataDTO.setShowOutreach(companyData.getShowOutreach());
        companyDataDTO.setSaidNoTo(companyData.getSaidNoTo());
        companyDataDTO.setTargetClient(companyData.getTargetClient());
        companyDataDTO.setEmailAction(companyData.getEmailAction());
        companyDataDTO.setMobile(companyData.getMobile());
        companyDataDTO.setOffice(companyData.getOffice());
        companyDataDTO.setAddress(companyData.getAddress());
        companyDataDTO.setCity(companyData.getCity());
        companyDataDTO.setZip(companyData.getZip());
        companyDataDTO.setSocialMedia(companyData.getSocialMedia());
        companyDataDTO.setNextFollowUp(companyData.getNextFollowUp());
        companyDataDTO.setInquiryStatus(companyData.getInquiryStatus());
        companyDataDTO.setWebsite(companyData.getWebsite());
        companyDataDTO.setVendorTour(companyData.getVendorTour());
        companyDataDTO.setCalendlySource(companyData.getCalendlySource());
        companyDataDTO.setRecommender(companyData.getRecommender());
        companyDataDTO.setInquiryOrigin(companyData.getInquiryOrigin());
        companyDataDTO.setId(companyData.getId());

        return companyDataDTO;
    }

    public List<companyDataDTO> getAllCompanies() {
        List<companyData> candidates = companyDataRepo.findByOrderByIdAsc();
        return candidates.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
