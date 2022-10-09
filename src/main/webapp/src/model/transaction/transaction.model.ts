import {WebUserPublic} from '../user/webuser-public.model';
import {Moment} from "moment";

export class Transaction {

    id?: string;

    uuid?: string;

    webUserInitiator?: WebUserPublic;

    bankAddressInitiator?: string;

    amountUsd?: number;

    webUserReceiver?: WebUserPublic;

    bankAddressReceiver?: string;

    transactionType?: TransactionType;

    at?: Date;

    constructor(data: Partial<Transaction>) {
        Object.assign(this, data);
    }


}

export enum TransactionType {
    PAYMENT, CASH_IN, CASH_OUT
}

