export class WebUserPublic {

    uuid?: string;
    email?: string;
    name?: string;
    picture?: string;

    constructor(data: Partial<WebUserPublic>) {
        Object.assign(this, data);
    }

}
