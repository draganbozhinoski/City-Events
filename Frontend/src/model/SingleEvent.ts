import { CityLocale } from './CityLocale';
import { Image } from './Image';

export interface SingleEvent {
  Prototype: {};

  locale: {
    id: Number;
    name: String;
    numReservations: Number;
    city: String;
    adult: Boolean;
    logoUrl: String;
    covidCertificate: Boolean;
    date: Number;
    description: String;
    locale: CityLocale;
    image: Image;
  };
}
