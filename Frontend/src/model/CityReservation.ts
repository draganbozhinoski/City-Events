import { CityLocale } from "./CityLocale";
import { CityTable } from "./CityTable";
import { CityUser } from "./CityUser";

export interface CityReservation {
    id:Number,
    name:String,
    description:String,
    phoneNumber:String,
    dateTime:String,
    table:CityTable, //TODO:
    locale:CityLocale,
    user:CityUser
}