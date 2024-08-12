package org.example;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRecord implements Comparable<MemberRecord> {
    @CsvBindByName(column = "Action")
    @CsvBindByPosition(position = 0)
    private Integer action;
    @CsvBindByName(column = "MemberID")
    @CsvBindByPosition(position = 1)
    private String memberId;
    @CsvBindByName(column = "MemberNo")
    @CsvBindByPosition(position = 2)
    private String memberNo;
    @CsvBindByName(column = "ActiveStatus")
    @CsvBindByPosition(position = 3)
    private Integer activeStatus;
    @CsvBindByName(column = "Surname")
    @CsvBindByPosition(position = 4)
    private String surname;
    @CsvBindByName(column = "GivenName")
    @CsvBindByPosition(position = 5)
    private String givenName;
    @CsvBindByName(column = "LongName")
    @CsvBindByPosition(position = 6)
    private String longName;
    @CsvBindByName(column = "BirthDate")
    @CsvBindByPosition(position = 7)
    private String birthDate;
    @CsvBindByName(column = "IdentificationNo")
    @CsvBindByPosition(position = 8)
    private String identificationNo;
    @CsvBindByName(column = "IdentificationExpiryDate")
    @CsvBindByPosition(position = 9)
    private String identificationExpiryDate;
    @CsvBindByName(column = "IssuanceCountryID")
    @CsvBindByPosition(position = 10)
    private String issuanceCountryId;
    @CsvBindByName(column = "IdentificationTypeID")
    @CsvBindByPosition(position = 11)
    private String identificationTypeId;
    @CsvBindByName(column = "Photo")
    @CsvBindByPosition(position = 12)
    private String photo;
    @CsvBindByName(column = "LastModifiedTime")
    @CsvBindByPosition(position = 13)
    private String lastModifiedTime;
    @Override
    public int compareTo(MemberRecord other) {
        return 1;

    }


}
