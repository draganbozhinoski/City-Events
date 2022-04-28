//package com.sorsix.cityevents.domain.view
//
//import com.sorsix.cityevents.domain.Image
//import com.sorsix.cityevents.domain.Locale
//import org.hibernate.annotations.Immutable
//import org.hibernate.annotations.Subselect
//import javax.persistence.*
//
//@Entity
//@Immutable
//@Subselect("select * from v_locales_reviews")
//data class EventWithReview {
//    @Id
//    @Column(name = "locale_id")
//    val eventId:Long,
//    @ManyToOne
//    @JoinColumn(name = "locale_id")
//    val locale: Locale,
//    @Column(name = "adult")
//    val adult:Boolean
//}