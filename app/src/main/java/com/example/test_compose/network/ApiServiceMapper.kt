package com.example.test_compose.network

interface ApiServiceMapper <Service,Property>{

    fun mapFromService(service:Service): Property

//    fun mapFromProperty(property:Property): Service
}