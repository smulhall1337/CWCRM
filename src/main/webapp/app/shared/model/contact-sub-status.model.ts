export interface IContactSubStatus {
    id?: number;
    name?: string;
}

export class ContactSubStatus implements IContactSubStatus {
    constructor(public id?: number, public name?: string) {}
}
