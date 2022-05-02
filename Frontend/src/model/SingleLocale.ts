export interface SingleLocale {
  Prototype: {};

  locale: {
    id: Number;
    name: String;
    type: String;
    tablesList: [];
    reservationsList: [];
    eventsList: [];
    reviewsList: [];
    logoUrl: String;
    numTables: Number;
  };
}
