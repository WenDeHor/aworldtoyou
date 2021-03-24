package com.example.aworldtoyou.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "botmessage")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idbot")
    private Long idbot;

    @Column(name = "botform")
    @Size(min = 0, max = 100)
    private String botform;

    @Column(name = "botphone")
    @Size(min = 0, max = 100)
    private String botphone;
}
