import { Moment } from 'moment';

export interface IAction {
    id?: number;
    dueDate?: Moment;
    assignedToFirstName?: string;
    assignedToId?: number;
    participantFirstName?: string;
    participantId?: number;
    priorityName?: string;
    priorityId?: number;
}

export class Action implements IAction {
    constructor(
        public id?: number,
        public dueDate?: Moment,
        public assignedToFirstName?: string,
        public assignedToId?: number,
        public participantFirstName?: string,
        public participantId?: number,
        public priorityName?: string,
        public priorityId?: number
    ) {}
}
