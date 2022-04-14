import { Byte } from "@angular/compiler/src/util";

export interface Image {
    "id":Number,
    "name":String | undefined,
    "type":String | undefined,
    "bytes":Byte[]
}