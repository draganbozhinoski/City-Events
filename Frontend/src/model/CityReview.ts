import { CityLocale } from "./CityLocale";

export interface CityReview {
    id:Number,
    review:String,
    stars:Number,
    locale:CityLocale
}