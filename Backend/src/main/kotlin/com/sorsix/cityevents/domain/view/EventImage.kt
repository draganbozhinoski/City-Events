package com.sorsix.cityevents.domain.view

import com.sorsix.cityevents.domain.Image
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Subselect
import javax.persistence.*

@Entity
@Immutable
@Subselect("select * from v_events_images")
data class EventImage (
    @Id
    @Column(name = "event_id")
    val eventId:Long,
    @ManyToOne
    @JoinColumn(name = "img_id")
    val image:Image,
    @Column(name = "adult")
    val adult:Boolean,
    @Column(name = "covid_certificate")
    val covidCertificate:Boolean

)