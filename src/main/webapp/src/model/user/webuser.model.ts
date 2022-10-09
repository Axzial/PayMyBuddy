export class WebUser {

    uuid?: string;
    email?: string;
    name?: string;
    picture?: string;
    walletUsd?: number;

    constructor(data: Partial<WebUser>) {
        Object.assign(this, data);
    }

}
