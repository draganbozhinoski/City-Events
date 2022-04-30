import { CityLocale } from "./CityLocale";
import { Image } from "./Image";

export interface CityEvent{
    id : Number,
    name: String,
    numReservations:Number,
    city:String,
    adult:Boolean,
    logoUrl:String,
    covidCertificate:Boolean,
    date:Date,
    description:String,
    locale:CityLocale,
    image:Image
}