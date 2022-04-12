import { CityLocale } from "./CityLocale"

export interface SingleEvent{
    Prototype:{},
   
    event:{ "id" : Number,
    "name": String,
    "numReservations":Number,
    "city":String,
    "adult":Boolean,
    "logoUrl":String,
    "covidCertificate":Boolean,
    "date":Number,
    "description":String,
    "locale":CityLocale
    }
}