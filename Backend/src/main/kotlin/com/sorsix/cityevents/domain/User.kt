package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sorsix.cityevents.domain.enums.UserType
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1,
    val username:String,
    val name:String,
    val email:String,
    val password:String,
    val phoneNumber:String,
    @Enumerated(EnumType.STRING)
    val type:UserType,
    @OneToOne(mappedBy = "owner")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val localeManages:Locale?,
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val reservation:List<Reservation>?
)