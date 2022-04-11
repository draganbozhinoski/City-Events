import { CityLocale } from "./CityLocale";

export interface CityEvent{
    "id" : Number,
    "name": String,
    "numReservations":Number,
    "city":String,
    "adult":Boolean,
    "logoUrl":String,
    "covidCertificate":Boolean,
    "date":Number,
    "locale":Number
}