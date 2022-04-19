import { Image } from "./Image";

export interface EventImage { 
    eventId:Number,
    image:Image,
    adult:Boolean,
    covidCertificate:Boolean,
    imageHere:Boolean
}