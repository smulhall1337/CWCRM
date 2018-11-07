export interface IDepartment {
    id?: number;
    name?: string;
}

export class Department implements IDepartment {
    constructor(public id?: number, public name?: string) {}
}
