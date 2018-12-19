export interface IAction {
    id?: number;
    userId?: number;
    participantId?: number;
}

export class Action implements IAction {
    constructor(public id?: number, public userId?: number, public participantId?: number) {}
}
