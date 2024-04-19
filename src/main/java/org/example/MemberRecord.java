package org.example;


import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRecord {
    @CsvBindByName(column = "Action")
    private Integer action;
    @CsvBindByName(column = "MemberID")
    private String memberId;
    @CsvBindByName(column = "MemberNo")
    private String memberNo;
    @CsvBindByName(column = "ActiveStatus")
    private Integer activeStatus;
    @CsvBindByName(column = "Surname")
    private String surname;
    @CsvBindByName(column = "GivenName")
    private String givenName;
    @CsvBindByName(column = "LongName")
    private String longName;
    @CsvBindByName(column = "BirthDate")
    private String birthDate;
    @CsvBindByName(column = "IdentificationNo")
    private String identificationNo;
    @CsvBindByName(column = "IdentificationExpiryDate")
    private String identificationExpiryDate;
    @CsvBindByName(column = "IssuanceCountryID")
    private String issuanceCountryId;
    @CsvBindByName(column = "IdentificationTypeID")
    private String identificationTypeId;
    @CsvBindByName(column = "Photo")
    private String photo;
    @CsvBindByName(column = "LastModifiedTime")
    private String lastModifiedTime;

}
