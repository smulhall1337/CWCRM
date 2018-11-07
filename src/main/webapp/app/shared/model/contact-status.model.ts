export interface IContactStatus {
    id?: number;
    name?: string;
    participantId?: number;
}

export class ContactStatus implements IContactStatus {
    constructor(public id?: number, public name?: string, public participantId?: number) {}
}
