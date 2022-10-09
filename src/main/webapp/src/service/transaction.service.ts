import {Transaction, TransactionType} from '@model/transaction/transaction.model';
import axios, {AxiosResponse} from 'axios';
import ApiConfig from '@config/ApiConfig';
import Routes from '@config/ApiConfig';
import PageDTO from "@utils/PageDTO";

export interface NewTransactionDTO {
    amountUsd: number;
    receiver: string;
    transactionType: TransactionType | string;
}

class TransactionService {

    postTransaction = (jwt: string, transaction: NewTransactionDTO): Promise<AxiosResponse<Transaction>> => {
        return axios.post<Transaction>(ApiConfig.BASE_URL + Routes.TRANSACTIONS_PATH, transaction, {headers: {'Authorization': `Bearer ${jwt}`}});
    }

    getTransactions = async (jwt: string): Promise<AxiosResponse<PageDTO<Transaction>>> => {
        return axios.get<PageDTO<Transaction>>(ApiConfig.BASE_URL + Routes.TRANSACTIONS_PATH, {headers: {'Authorization': `Bearer ${jwt}`}});
    }

}

export default new TransactionService();
