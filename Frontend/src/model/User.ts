import { CityLocale } from "./CityLocale";

export interface User{
    id:Number,
    username:String,
    name:String,
    email:String,
    password:String,
    phoneNumber:String,
    localeManages:CityLocale
}