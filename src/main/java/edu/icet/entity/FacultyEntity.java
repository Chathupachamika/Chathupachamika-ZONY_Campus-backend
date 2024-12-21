package edu.icet.entity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faculty")
public class FacultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;

    private String name;
    private String description;
    private String specializations;
    private String icon;
    private String facImageName;
    private String facImageType;

    @Lob
    @Column(name = "fac_image_data", columnDefinition = "BLOB")
    private byte[] facImageData;

}