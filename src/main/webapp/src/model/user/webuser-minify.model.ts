export class WebUserMinify {

    uuid?: string;
    email?: string;
    name?: string;

    constructor(data: Partial<WebUserMinify>) {
        Object.assign(this, data);
    }

}
